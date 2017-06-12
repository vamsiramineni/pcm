package gov.samhsa.c2s.pcm.service;

import gov.samhsa.c2s.pcm.service.dto.ConsentAttestationDto;
import gov.samhsa.c2s.pcm.service.dto.ConsentDto;
import gov.samhsa.c2s.pcm.service.dto.ConsentRevocationDto;
import gov.samhsa.c2s.pcm.service.dto.ConsentTermDto;
import gov.samhsa.c2s.pcm.service.dto.DetailedConsentDto;
import gov.samhsa.c2s.pcm.service.dto.SensitivityCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ConsentService {
    @Transactional
    Page<DetailedConsentDto> getConsents(String patientId, Optional<Long> purposeOfUse, Optional<Long> fromProvider, Optional<Long> toProvider, Optional<Integer> page, Optional<Integer> size);

    @Transactional
    void saveConsent(String patientId, ConsentDto consentDto, Optional<String> createdBy);

    @Transactional
    void deleteConsent(String patientId, Long consentId, Optional<String> lastUpdatedBy);

    @Transactional
    void updateConsent(String patientId, Long consentId, ConsentDto consentDto, Optional<String> lastUpdatedBy);

    @Transactional
    void attestConsent(String patientId, Long consentId, ConsentAttestationDto consentAttestationDto, Optional<String> attestedBy, Optional<Boolean> attestedByPatient);

    @Transactional
    void revokeConsent(String patientId, Long consentId, ConsentRevocationDto consentRevocationDto);

    @Transactional(readOnly = true)
    Object getConsent(String patientId, Long consentId, String format);

    @Transactional(readOnly = true)
    Object getAttestedConsent(String patientId, Long consentId, String format);

    @Transactional(readOnly = true)
    Object getRevokedConsent(String patientId, Long consentId, String format);

    @Transactional(readOnly = true)
    ConsentTermDto getConsentAttestationTerm(Optional<Long> id);

    @Transactional(readOnly = true)
    ConsentTermDto getConsentRevocationTerm(Optional<Long> id);

    @Transactional(readOnly = true)
    List<SensitivityCategoryDto> getSharedSensitivityCategories(String patientId, Long consentId);

}
