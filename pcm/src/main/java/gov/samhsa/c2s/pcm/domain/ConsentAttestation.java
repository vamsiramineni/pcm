package gov.samhsa.c2s.pcm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Audited
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsentAttestation {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private Consent consent;

    @ManyToOne
    @NotNull
    private ConsentAttestationTerm consentAttestationTerm;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] consentAttestationPdf;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fhirConsent;

    @OneToMany(mappedBy = "consentAttestation", cascade = CascadeType.ALL)
    @NotAudited
    private List<Practitioner> fromPractitioners = new ArrayList<>();

    @OneToMany(mappedBy = "consentAttestation", cascade = CascadeType.ALL)
    @NotAudited
    private List<Organization> fromOrganizations = new ArrayList<>();

    @OneToMany(mappedBy = "consentAttestation", cascade = CascadeType.ALL)
    @NotAudited
    private List<Practitioner> toPractitioners = new ArrayList<>();

    @OneToMany(mappedBy = "consentAttestation", cascade = CascadeType.ALL)
    @NotAudited
    private List<Organization> toOrganizations = new ArrayList<>();
}