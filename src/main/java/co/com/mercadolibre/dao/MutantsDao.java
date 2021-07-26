package co.com.mercadolibre.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUTANTS")
public class MutantsDao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DNA")
    private String dna;
    @Column(name = "ISMUTANT")
    private Boolean isMutant;

    public MutantsDao() {
        
    }

    public MutantsDao(String dna, Boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the dna
     */
    public String getDna() {
        return dna;
    }

    /**
     * @param dna the dna to set
     */
    public void setDna(String dna) {
        this.dna = dna;
    }

    /**
     * @return Boolean return the isMutant
     */
    public Boolean getIsMutant() {
        return isMutant;
    }

    /**
     * @param isMutant the isMutant to set
     */
    public void setIsMutant(Boolean isMutant) {
        this.isMutant = isMutant;
    }

}