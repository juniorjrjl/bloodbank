package com.wktechnology.bloodbank.domain.service.query.impl;

import com.wktechnology.bloodbank.domain.dto.AgesBloodTypeDTO;
import com.wktechnology.bloodbank.domain.dto.AvengeIMCPerAgeDTO;
import com.wktechnology.bloodbank.domain.dto.BloodTypeAvengeAgeDTO;
import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.dto.DonatesPerBloodReceiverDTO;
import com.wktechnology.bloodbank.domain.dto.IMCAndAgeDTO;
import com.wktechnology.bloodbank.domain.dto.PercentObesePerSexDTO;
import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;
import com.wktechnology.bloodbank.domain.repository.CandidateCriteriaRepository;
import com.wktechnology.bloodbank.domain.service.query.BloodTypeQueryService;
import com.wktechnology.bloodbank.domain.service.query.CandidateQueryService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@AllArgsConstructor
@Service
public class CandidateQueryServiceImpl implements CandidateQueryService {

    private final CandidateCriteriaRepository criteriaRepository;
    private final BloodTypeQueryService bloodTypeQueryService;

    @Override
    public List<CandidatePerStateDTO> amountPerState() {
        return criteriaRepository.amountPerState();
    }

    @Override
    public List<BloodTypeAvengeAgeDTO> avengeAgePerBloodType() {
        var birthdateAndBloodTypes = criteriaRepository.agesAndBloodType();
        List<BloodTypeAvengeAgeDTO> bloodTypeAvengeAge = new ArrayList<>();
        while (CollectionUtils.isNotEmpty(birthdateAndBloodTypes)){
            var currentBloodType = birthdateAndBloodTypes.get(0).getBloodType();
            var currentCalc = BloodTypeAvengeAgeDTO.builder().bloodType(currentBloodType).build();
            var stored = birthdateAndBloodTypes.stream().filter(b -> b.getBloodType().equals(currentBloodType)).toList();
            var agesSum = stored.stream()
                    .map(AgesBloodTypeDTO::getAge)
                    .reduce(0, Integer::sum);
            currentCalc = currentCalc.toBuilder().avenge((long) (agesSum / stored.size())).build();
            bloodTypeAvengeAge.add(currentCalc);
            var typesCalculated = bloodTypeAvengeAge.stream().map(BloodTypeAvengeAgeDTO::bloodType).toList();
            birthdateAndBloodTypes = birthdateAndBloodTypes.stream().filter(b -> !typesCalculated.contains(b.getBloodType())).toList();
        }
        return bloodTypeAvengeAge;
    }

    @Override
    public List<PercentObesePerSexDTO> percentObesesPerSex() {
        var imcAndSexes = criteriaRepository.imcAndSex();
        List<PercentObesePerSexDTO> percentObesesPerSex = new ArrayList<>();
        while (CollectionUtils.isNotEmpty(imcAndSexes)){
            var currentSex = imcAndSexes.get(0).getSex();
            var currentCalc = PercentObesePerSexDTO.builder().sex(currentSex).build();
            var stored = imcAndSexes.stream().filter(c -> c.getSex().equals(currentSex)).toList();
            var obeseAmount = stored.stream().filter(s -> s.getImc().compareTo(new BigDecimal(30)) > 0).count();
            var percent = new BigDecimal(obeseAmount * 100).divide(new BigDecimal(stored.size()), 2 , HALF_UP);
            currentCalc = currentCalc.toBuilder().percent(percent).build();
            percentObesesPerSex.add(currentCalc);
            var typesCalculated = percentObesesPerSex.stream().map(PercentObesePerSexDTO::sex).toList();
            imcAndSexes = imcAndSexes.stream().filter(b -> !typesCalculated.contains(b.getSex())).toList();
        }
        return percentObesesPerSex;
    }

    @Override
    public List<AvengeIMCPerAgeDTO> avengeIMCPerRangeAge() {
        var imcAndAge = criteriaRepository.imcAndAge();
        List<AvengeIMCPerAgeDTO> avengesIMCPerAges = new ArrayList<>();
        var maxAge = 10;
        while (CollectionUtils.isNotEmpty(imcAndAge)){
            var ageRangeLabel = String.format("%s..%s", maxAge -10, maxAge);
            var currentCalc = AvengeIMCPerAgeDTO.builder().ageRange(ageRangeLabel).build();
            int maxAgeFilterStored = maxAge;
            var stored = imcAndAge.stream().filter(i -> i.getAge() <= maxAgeFilterStored).toList();
            if (CollectionUtils.isNotEmpty(stored)){
                var imcAmount = stored.stream().map(IMCAndAgeDTO::getImc).reduce(BigDecimal.ZERO, BigDecimal::add);
                var avengeIMC = imcAmount.divide(new BigDecimal(stored.size()), 2, HALF_UP);
                currentCalc = currentCalc.toBuilder().avengeIMC(avengeIMC).build();
                avengesIMCPerAges.add(currentCalc);
                int maxAgeFilterMainList = maxAge;
                imcAndAge = imcAndAge.stream().filter(i -> i.getAge() > maxAgeFilterMainList).toList();
            }
            maxAge += 10;
        }
        return avengesIMCPerAges;
    }

    @Override
    public List<DonatesPerBloodReceiverDTO> possibleDonatesPerBloodReceiver() {
        var bloodTypes = bloodTypeQueryService.findAll();
        List<DonatesPerBloodReceiverDTO> types = new ArrayList<>();
        for (BloodTypeEntity bloodType : bloodTypes) {
            var currentType = DonatesPerBloodReceiverDTO.builder().bloodType(bloodType.getName()).build();
            var givers = criteriaRepository.findGiverPerBloodType(bloodType.getId());
            currentType = currentType.toBuilder().donates(givers).build();
            types.add(currentType);
        }
        return types;
    }

}
