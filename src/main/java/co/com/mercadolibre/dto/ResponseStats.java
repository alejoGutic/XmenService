package co.com.mercadolibre.dto;

public class ResponseStats {

    private String count_mutant_dna;
    private String count_human_dna;
    private String ratio;
    
    public ResponseStats(String count_mutant_dna, String count_human_dna, String ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    /**
     * @return String return the count_mutant_dna
     */
    public String getCount_mutant_dna() {
        return count_mutant_dna;
    }

    /**
     * @param count_mutant_dna the count_mutant_dna to set
     */
    public void setCount_mutant_dna(String count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    /**
     * @return String return the count_human_dna
     */
    public String getCount_human_dna() {
        return count_human_dna;
    }

    /**
     * @param count_human_dna the count_human_dna to set
     */
    public void setCount_human_dna(String count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    /**
     * @return String return the ratio
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * @param ratio the ratio to set
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

}