public class Portefeuille
{
    private Cryptomonnaie monnaie;
    private double        montant; // Soit le nombre de jetons
    private String        proprietaire;

    public Portefeuille(Cryptomonnaie monnaie, double montant, String proprietaire)
    {
        this.monnaie      = monnaie;
        this.montant      = montant;
        this.proprietaire = proprietaire;
    }

    /**
     * Cette fonction vous permet de transfĂ©rer des devises du portefeuille actuel 
     * vers le portefeuille de destination pour le montant indiquĂ©. Le type de devise 
     * (nom du Jeton) doit Ăªtre le mĂªme entre les deux portefeuilles et le montant 
     * du portefeuille actuel doit Ăªtre supĂ©rieur ou Ă©gal Ă  celui indiquĂ©.
     * @param destination 
     * @param montantJetons
     * @return Vrai si la transaction a Ă©tĂ© effectuĂ©e, faux sinon.  
     */
    public boolean transfertDevise (Portefeuille destination, double montantJetons)
    {
        if ( destination == null              || 
             montantJetons < 0                ||
             this.montant - montantJetons < 0 || 
             this.monnaie.getNom() != destination.monnaie.getNom() ) return false;
        
        this.montant -= montantJetons;
        destination.montant += montantJetons;
        return true;
    }

    /**
     * Cette fonction vous permet d'acheter des jetons de la 
     * crypto-devise en fonction de leur valeur en euros. 
     * Le rĂ©sultat est l'augmentation des jetons de la crypto-monnaie.
     * @param montantEuros Valeur d'achat en euros 
     * @return true si le montant en euros est supĂ©rieur ou Ă©gal Ă  0 
     */

    public boolean achatDevise (double montantEuros)
    {
        if ( montantEuros < 0 ) return false;

        double res;
        res = montantEuros/this.monnaie.getValeurDeJeton();
        this.montant += res;
        return true;
    }

    /**
     * Valide si le proprietaire passĂ© en parametre est celui
     * qui as le portefeuille
     * @param proprietaire
     * @return true si les nom du propriĂ©taire est correct
     */

    public boolean estProprietaire (String proprietaire)
    {
        return (proprietaire.equals(this.proprietaire))?true:false;
    }

    /**
     * 
     * @return La valeur en euros du Portefeuille. 
     * Autrement dit, le monant de jetons multipliĂ© par la valeur des jetons. 
     */
    public double valeurEnEuros()
    {
        return this.montant * this.monnaie.getValeurDeJeton();
    }

    public String getProprietaire() 
    {
        return proprietaire;
    }

    public Cryptomonnaie getMonnaie() 
    {
        return monnaie;
    }

    public double getMontant() 
    {
        return montant;
    }

    @Override
    public String toString() {
        return String.format("%10s",proprietaire) + " : "
            + String.format("%10.1f", montant)   + " x " 
            + this.monnaie.toString()            + " = "
            + String.format("%10.1f", valeurEnEuros());
    }

}