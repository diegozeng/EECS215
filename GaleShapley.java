/* Input: Two sets of equal size + the preferences of each element.
 * Goal: Find a perfect stable matching
 * Data structure: ArrayList (fixed length, LinkedList is stronger at inserting and removing)
 */
 
/** Class GaleShapley **/
public class GaleShapley
{
    private int N;
    private String[][] menPref;
    private String[][] womenPref;
    private String[] men;
    private String[] women;
    private String[] womenPartner;
    private boolean[] menEngaged;
    private int engagedCount;
    
    /** Constructor **/
    public GaleShapley(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        N = mp.length;
        engagedCount = 0;
        men = m;
        women = w;
        menPref = mp;
        womenPref = wp;
        menEngaged = new boolean[N];
        womenPartner = new String[N];
        stableMatching();
    }
    
    /** Main function to stable matching **/
    private void stableMatching()
    {
        while (engagedCount < N) //Some man is free and hasn't proposed to every woman
        {
            int free;
            for (free = 0; free < N; free++)
                if (!menEngaged[free])
                    break;
            
            for (int i = 0; i < N && !menEngaged[free]; i++)
            {
                int index = womenIndexOf(menPref[free][i]);
                if (womenPartner[index] == null) // if (w is free)
                {
                    womenPartner[index] = men[free];
                    menEngaged[free] = true;
                    engagedCount++;
                }
                else
                {
                    String currentPartner = womenPartner[index];
                    if (morePreference(currentPartner, men[free], index)) // else if (w prefers m to her partner m')
                    {
                        womenPartner[index] = men[free];
                        menEngaged[free] = true;
                        menEngaged[menIndexOf(currentPartner)] = false;
                    }
                    else {} //w rejects m, m remains free
                }
            }
        }
        System.out.println("Partners are : ");
        System.out.println("(Men)" + " " + "(Women)");
        for (int i = 0; i < N; i++)
        {
            System.out.println(womenPartner[i] +" "+ women[i]);
        }
    }
    
    /** function to check if women prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (womenPref[index][i].equals(newPartner))
                return true;
            if (womenPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    
    /** get men index **/
    private int menIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (men[i].equals(str))
                return i;
        return -1;
    }
    
    /** get women index **/
    private int womenIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (women[i].equals(str))
                return i;
        return -1;
    }
    
    /** main function **/
    public static void main(String[] args)
    {
        System.out.println("Gale－Shapley Algorithm(GS):");
        /** list of men **/
        String[] m = {"Victor", "Wyatt", "Xavier", "Yancey", "Zeus"};
        /** list of women **/
        String[] w = {"Amy", "Bertha", "Clare", "Diane", "Erika"};
        
        /** men preference **/
        String[][] mp = {{"Bertha", "Amy", "Diane", "Erika", "Clare"},
            {"Diane", "Bertha", "Amy", "Clare", "Erika"},
            {"Bertha", "Erika", "Clare", "Diane", "Amy"},
            {"Amy", "Diane", "Clare", "Bertha", "Erika"},
            {"Bertha", "Diane", "Amy", "Erika", "Clare"}};
        /** women preference **/
        String[][] wp = {{"Zeus", "Victor", "Wyatt", "Yancey", "Xavier"},
            {"Xavier", "Wyatt", "Yancey", "Victor", "Zeus"},
            {"Wyatt", "Xavier", "Yancey", "Zeus", "Victor"},
            {"Victor", "Zeus", "Yancey", "Xavier", "Wyatt"},
            {"Yancey", "Wyatt", "Zeus", "Xavier", "Victor"}};
        
        GaleShapley gs = new GaleShapley(m, w, mp, wp);
    }
}