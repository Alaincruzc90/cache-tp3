import java.util.Random;

/**
 * Created by esteban on 17/07/2017.
 * This class is used for doing test on the application.
 */
public class PerformanceTest {

    //This array contains all the title that can possibly be requested to the application. In most cases only a portion of the title will be used.
    static String[] testSubjects = {"AccessibleComputing", "Anarchism", "AfghanistanHistory", "AfghanistanGeography", "AfghanistanPeople", "AfghanistanCommunications", "AfghanistanTransportations", "AfghanistanMilitary", "AfghanistanTransnationalIssues", "AssistiveTechnology", "AmoeboidTaxa", "Autism", "AlbaniaHistory", "AlbaniaPeople", "AsWeMayThink", "AlbaniaGovernment", "AlbaniaEconomy", "Albedo", "AfroAsiaticLanguages", "ArtificalLanguages", "AbacuS", "AbalonE", "AbbadideS", "AbbesS", "AbbevilleFrance", "AbbeY", "AbboT", "Abbreviations", "AtlasShrugged", "ArtificialLanguages", "AtlasShruggedCharacters", "AtlasShruggedCompanies", "AyersMusicPublishingCompany", "AfricanAmericanPeople", "AdolfHitler", "AbeceDarians", "AbeL", "AbensbergGermany", "AberdeenSouthDakota", "ArthurKoestler", "AynRand", "AlexanderTheGreat", "AnchorageAlaska", "ArgumentForms", "ArgumentsForTheExistenceOfGod", "AnarchY", "AsciiArt", "AcademyAwards", "AcademyAwards/BestPicture", "AustriaLanguage", "AcademicElitism", "AxiomOfChoice", "AmericanFootball", "AnnaKournikova", "AndorrA", "AustroAsiaticLanguages", "ActresseS", "A", "AnarchoCapitalism", "AnarchoCapitalists", "ActressesS", "AnAmericanInParis", "AutoMorphism", "ActionFilm", "Alabama", "AfricA", "Achilles", "AppliedStatistics", "Abraham_Lincoln", "Aristotle", "An_American_in_Paris", "Academy_Award_for_Best_Production_Design", "Academy_Awards", "Action_Film", "Actrius", "Animalia_(book)", "International_Atomic_Time", "Altruism", "AutoRacing", "Ayn_Rand", "Alain_Connes", "Allan_Dwan", "Algeria/People", "Algeria/Transnational_Issues", "Algeria", "List_of_Atlas_Shrugged_characters", "Topics_of_note_in_Atlas_Shrugged", "Anthropology", "Agricultural_science", "Alchemy", "Air_Transport", "Alien", "Astronomer", "Ameboid_stage", "ASCII", "Ashmore_And_Cartier_Islands", "Austin_(disambiguation)", "Animation", "Apollo", "Andre_Agassi", "Artificial_languages", "Austroasiatic_languages", "Afro-asiatic_languages", "Afroasiatic_languages", "Andorra", "Andorra/Transnational_issues", "Arithmetic_mean", "American_Football_Conference", "Albert_Gore", "AnEnquiryConcerningHumanUnderstanding", "Animal_Farm", "Amphibian", "Albert_Arnold_Gore/Criticisms", "Alaska", "Auteur_Theory_Film", "Agriculture", "Aldous_Huxley", "Abstract_Algebra", "Ada", "Aberdeen_(disambiguation)", "Algae", "Analysis_of_variance", "ANOVA", "Alkane", "Appellate_procedure_in_the_United_States", "Answer_(law)", "Appellate_court", "Arithmetic_and_logic_unit", "Actress", "Arraignment", "America_the_Beautiful", "Assistive_technology", "Accessible_computing", "Abacus", "Acid", "Asphalt", "American_National_Standards_Institute", "Argument_(disambiguation)", "Apollo_11", "Apollo_8", "Astronaut", "A_Modest_Proposal", "Alkali_metal", "Argument_form", "Allotrope", "Alphabet", "Atomic_number", "Anatomy", "Affirming_the_consequent", "Andrei_Tarkovsky", "Ambiguity", "Abel", "Animal_(disambiguation)", "Aardvark", "Aardwolf", "Adobe", "Adventure", "Amaltheia", "Analysis_of_Variance", "Asia", "Aruba", "Articles_of_Confederation", "Archaeology/Broch", "Asia_Minor_(disambiguation)", "Aa_River", "Atlantic_Ocean", "Arthur_Schopenhauer", "Angola", "Demographics_of_Angola", "Politics_of_Angola", "Economy_of_Angola", "Transport_in_Angola", "Angolan_Armed_Forces", "Foreign_relations_of_Angola", "Albert_Sidney_Johnston", "Android_(robot)", "Alberta", "Adding_Wikipedia_articles_to_Nupedia", "Astronomy/History", "List_of_anthropologists", "Astronomy_and_Astrophysics/History", "Actinopterygii", "Al_Gore/Criticisms", "Albert_Einstein", "Afghanistan", "Albania", "Allah", "Algorithms_(journal)", "Antigua_And_Barbuda", "Azerbaijan", "Amateur_astronomy", "Astronomers_and_Astrophysicists", "Aikido", "Art", "Albania/History", "Albania/Transnational_Issues", "Albania/People", "Albania/Foreign_relations", "Agnostida", "Abortion", "Abstract_(law)", "A.E._van_Vogt", "AOLamer", "American_Revolutionary_War", "Ampere", "Algorithm", "Annual_plant", "Anthophyta", "Atlas_(disambiguation)", "Mouthwash", "Alexander_the_Great", "Alfred_Korzybski", "Asteroids_(video_game)", "Asparagales", "Alismatales", "Apiales", "Asterales", "Asteroid", "Allocution", "Affidavit", "Aries_(constellation)", "Aquarius_(constellation)", "Anime", "Asterism", "Ankara", "Arabic", "AlbaniaCommunications", "Alfred_Hitchcock", "Anaconda", "Afghanistan/History", "Afghanistan/Geography", "Afghanistan/Government", "Afghanistan/People", "Afghanistan/Economy", "Afghanistan/Communications", "Afghanistan/Military", "Afghanistan/Transnational_Issues", "Afghanistan_(1911_Encyclopedia)", "Altaic_languages", "Austrian_German", "Austria/Transnational_issues", "Anglican_Church", "Axiom_of_choice", "Attila", "Aegean_Sea", "A_Clockwork_Orange_(novel)", "Amsterdam", "Museum_of_Work", "Audi", "Aircraft", "Alfred_Nobel", "Alexander_Graham_Bell", "Anatolia", "Abiotic_factors", "Apple_Inc.", "Aberdeenshire", "AU", "Aztlan_Underground", "Aland", "American_Civil_War", "Andy_Warhol", "Alp_Arslan", "American_Film_Institute", "Akira_Kurosawa", "Ancient_civilization", "Ancient_Egypt", "Analog_Brothers", "Motor_neuron_disease", "Abjad", "Abugida", "ABBA", "Allegiance", "Absolute_majority", "Altenberg", "MessagePad", "A._E._van_Vogt", "Anna_Kournikova", "Accountancy", "Alfons_Maria_Jakob", "Agnosticism", "Argon", "Arsenic", "Antimony", "Actinium", "Americium", "Astatine", "Atom", "Arable_land", "Aluminium", "Advanced_Chemistry", "Awk", "AgoraNomic", "Anglican_Communion", "Arne_Kaijser", "Archipelago", "Author", "Andrey_Markov", "Anti-semitism", "Anti-semitic", "Angst", "Anxiety", "A.A._Milne", "A._A._Milne", "Asociaci├│n_Alumni", "Alumna", "Axiom", "Alpha", "Alvin_Toffler", "The_Amazing_Spider-Man", "AM", "Automated_Alice/XII", "Automated_Alice/XI", "Automated_Alice/X", "Automated_Alice/IX", "Automated_Alice/VIII", "Automated_Alice/VI", "Automated_Alice/VII", "Automated_Alice/V", "Automated_Alice/IV", "Automated_Alice/II", "Automated_Alice/I", "Automated_Alice/III", "Antigua_and_Barbuda", "Azincourt", "Albert_Speer", "Asteraceae", "Apiaceae", "Axon", "Agma", "Aramaic_alphabet", "Arguments_for_the_existence_of_God", "American_shot", "Acute_disseminated_encephalomyelitis", "Ataxia", "AmbientCalculusOnline", "Abdul_Alhazred", "A_priori_and_a_posterior_knowledge", "Ada_Lovelace", "AmbientCalculiOnline", "August_Derleth", "Alps", "A_priori_and_a_posteriori_knowledge", "Albert_Camus", "Agatha_Christie", "The_Plague", "Applied_ethics", "Absolute_value", "Analog_signal", "Arecales", "Hercule_Poirot", "Miss_Marple", "April", "August", "Aaron", "April_6", "April_12", "April_15", "April_30", "August_22", "August_27", "Alcohol", "Achill_Island", "Allen_Ginsberg", "Algebraically_closed_field", "August_6", "Anatoly_Karpov", "Aspect_ratio", "Auto_racing", "Anarcho-capitalism", "Anarcho-capitalists", "August_9", "Aristophanes", "Albert_Schweitzer", "Austrian_School", "Abscess", "Aal", "Aalborg_Municipality", "Aarhus", "Northern_cavefish", "Abatement", "Amateur", "Alexis_Carrel", "All_Souls'_Day", "Anatole_France", "Andr├®_Gide", "Applied_statistics", "Analysis_of_variance/Random_effects_models", "Analysis_of_variance/Degrees_of_freedom", "Algorithms_for_calculating_variance", "Almond", "Demographics_of_Antigua_and_Barbuda", "Politics_of_Antigua_and_Barbuda", "Telecommunications_in_Antigua_and_Barbuda", "Royal_Antigua_and_Barbuda_Defence_Force", "Antigua_and_Barbuda/Transnational_issues", "Antisemitism", "Economy_of_Azerbaijan", "Geography_of_Azerbaijan", "Azerbaijan/People", "Azerbaijan/Communications", "Foreign_relations_of_Azerbaijan", "Azerbaijani_Armed_Forces", "Azerbaijan/Foreign_relations", "Geography_of_Armenia", "Demographics_of_Armenia", "Politics_of_Armenia", "Economy_of_Armenia", "Transport_in_Armenia", "Armed_Forces_of_Armenia", "Foreign_relations_of_Armenia", "Argentina/Transnational_issues", "Argentina/Foreign_relations", "Geography_of_American_Samoa", "Demographics_of_American_Samoa", "Politics_of_American_Samoa", "Economy_of_American_Samoa", "Transportation_in_American_Samoa", "American_Samoa/Military", "Australia/Transnational_issues", "August_13", "Avicenna", "The_Ashes", "Analysis", "Abner_Doubleday", "America's_National_Game", "Amplitude_modulation", "Augustin-Jean_Fresnel", "Abbot", "Ardipithecus", "Assembly_line", "Adelaide", "AK47", "Alan_Garner", "Amhrann_na_bhFiann", "August_2", "Atlantic_(disambiguation)", "Algebraic_number", "Automorphism", "Accordion", "Artificial_intelligence", "Afro_Celt_Sound_System", "Ancient_philosophy", "Anaximander", "APL", "Architect", "Abbreviation", "Aphrodite", "April_1", "Antisymmetric_relation", "Aleister_Crowley", "Afterlife", "Admiral_Doenitz", "Astrometry", "Athena", "Amber_Diceless_Roleplaying_Game", "Athene_(disambiguation)", "AphexTwin", "Alloy", "Articles_of_Faith", "Alternative_history", "Artistic_revolution", "Agrarianism", "Atomic", "Allotropes", "Angle", "Asa", "Acoustics", "Angle_tribe", "Atomic_physics", "American_Sign_Language", "Applet", "Alternate_history", "Atomic_orbitals", "Atomic_orbital", "Amino_acid", "Alan_Turing", "Area", "Astronomical_unit", "Artist", "Actaeon", "Anglicanism", "Athens", "Anguilla", "Anguilla/Transnational_issues", "Anguilla/Military", "Telecommunications_in_Anguilla", "Ashmore_and_Cartier_Islands", "Ashmore_and_Cartier_Islands/Geography", "Ashmore_and_Cartier_Islands/People", "Ashmore_and_Cartier_Islands/Government", "Ashmore_and_Cartier_Islands/Transportation", "Ashmore_and_Cartier_Islands/Economy", "Ashmore_and_Cartier_Islands/Military", "Acoustic_theory", "Alexander_Mackenzie_(politician)", "Atomic_bomb", "Ashoka", "American_(word)", "Ada_(programming_language)", "Alpha_ray"};


    public static void main(String args[]) {

        //
        //Tests with 50 total accesses
        //

        System.out.println("Test 1.1 started.");

        //Test where numberOfTotalPageAccess == 50. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 50, 12, -1);
        test(true, 50, 12, 3);
        test(true, 50, 12, 6);
        test(true, 50, 12, 12);
        test(false, 50, 12, 0);

        System.out.println("Test 1.1 finished.");

        System.out.println("Test 1.2 started.");

        //Test where numberOfTotalPageAccess == 50. numberOfPages == numberOfTotalPageAccess/2. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 50, 25, -1);
        test(true, 50, 25, 6);
        test(true, 50, 25, 12);
        test(true, 50, 25, 25);
        test(false, 50, 25, 0);

        System.out.println("Test 1.2 finished.");

        System.out.println("Test 1.3 started.");

        //Test where numberOfTotalPageAccess == 50. numberOfPages == numberOfTotalPageAccess. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 50, 50, -1);
        test(true, 50, 50, 12);
        test(true, 50, 50, 25);
        test(true, 50, 50, 50);
        test(false, 50, 50, 0);

        System.out.println("Test 1.3 finished.");

        //
        //Tests with 100 total accesses
        //

        System.out.println("Test 2.1 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 100, 25, -1);
        test(true, 100, 25, 6);
        test(true, 100, 25, 12);
        test(true, 100, 25, 25);
        test(false, 100, 25, 0);

        System.out.println("Test 2.1 finished.");

        System.out.println("Test 2.2 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 100, 50, -1);
        test(true, 100, 50, 12);
        test(true, 100, 50, 25);
        test(true, 100, 50, 50);
        test(false, 100, 50, 0);

        System.out.println("Test 2.2 finished.");

        System.out.println("Test 2.3 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 100, 100, -1);
        test(true, 100, 100, 12);
        test(true, 100, 100, 25);
        test(true, 100, 100, 50);
        test(false, 100, 100, 0);

        System.out.println("Test 2.3 finished.");

        //
        //Tests with 200 total accesses
        //

        System.out.println("Test 3.1 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 200, 50, -1);
        test(true, 200, 50, 12);
        test(true, 200, 50, 25);
        test(true, 200, 50, 50);
        test(false, 200, 50, 0);

        System.out.println("Test 3.1 finished.");

        System.out.println("Test 3.2 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 200, 100, -1);
        test(true, 200, 100, 25);
        test(true, 200, 100, 50);
        test(true, 200, 100, 100);
        test(false, 200, 100, 0);

        System.out.println("Test 3.2 finished.");

        System.out.println("Test 3.3 started.");

        //Test where numberOfTotalPageAccess == 100. numberOfPages == numberOfTotalPageAccess/4. CacheSizes are default, numberOfPages/4, numberOfPages/2, numberOfPages and inactive.
        test(true, 200, 200, -1);
        test(true, 200, 200, 50);
        test(true, 200, 200, 100);
        test(true, 200, 200, 200);
        test(false, 200, 200, 0);

        System.out.println("Test 3.3 finished.");

    }

    /* Method to test the cache with the different parameters.
     * cacheActive is true when the cache is active for the test and false when it is not.
     * numberOfTotalPageAccess is the amount of requests made. The amount of times getPage() is invoked.
     * numberOfPages is the amount of possible pages that the test will request to the application. If numberOfPages = n then the test uses n title to choose from when requesting a page.
     * cacheSize is the amount maximum of elements the cache can have.
     */
    private static void test(boolean cacheActive, int numberOfTotalPageAccess, int numberOfPages, int cacheSize){
        Random RNG = new Random();

        Service service = new Service(cacheActive, cacheSize, -1 , -1);

        long start = System.nanoTime();

        for(int i=0;i < numberOfTotalPageAccess; i++){
            int page = RNG.nextInt(numberOfPages);
            service.getPage(testSubjects[page]);
            //System.out.println(res);
        }

        long finish = System.nanoTime();
        System.out.println((finish - start)/1000000);

        service.finishService();
    }

}
