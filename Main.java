import java.util.*;

class CropSoilManagementSystem {
    private Map<String, CropConditions> crops = new HashMap<>();

    public CropSoilManagementSystem() {
        // Initialize the crop data with soil pH range, moisture levels, and nutrient requirements
        crops.put("Wheat", new CropConditions(6.0, 7.5, 30, 50, new NutrientLevels(80, 60, 40)));
        crops.put("Rice", new CropConditions(5.5, 7.0, 40, 70, new NutrientLevels(100, 80, 60)));
        crops.put("Maize", new CropConditions(5.5, 7.5, 35, 50, new NutrientLevels(90, 70, 50)));
        crops.put("Soybean", new CropConditions(6.0, 7.0, 20, 40, new NutrientLevels(70, 50, 30)));
        crops.put("Cotton", new CropConditions(5.8, 7.0, 25, 45, new NutrientLevels(60, 40, 30)));
        
        // Additional Crops
        crops.put("Barley", new CropConditions(6.0, 7.5, 30, 60, new NutrientLevels(70, 50, 40)));
        crops.put("Potato", new CropConditions(4.8, 5.5, 60, 80, new NutrientLevels(120, 100, 80)));
        crops.put("Tomato", new CropConditions(6.0, 7.0, 50, 70, new NutrientLevels(100, 80, 60)));
        crops.put("Carrot", new CropConditions(5.5, 7.0, 60, 80, new NutrientLevels(70, 60, 50)));
        crops.put("Peanut", new CropConditions(5.9, 7.0, 30, 50, new NutrientLevels(80, 60, 40)));
    }

    public List<String> getRecommendation(double soilPh, int moistureLevel, NutrientLevels soilNutrients) {
        List<String> suitableCrops = new ArrayList<>();
        for (Map.Entry<String, CropConditions> entry : crops.entrySet()) {
            CropConditions conditions = entry.getValue();
            if (conditions.isSuitable(soilPh, moistureLevel, soilNutrients)) {
                suitableCrops.add(entry.getKey());
            }
        }
        return suitableCrops;
    }

    public String pestControlSuggestion(String crop) {
        Map<String, String[]> pests = new HashMap<>();
        pests.put("Wheat", new String[] {"Aphids", "Armyworms"});
        pests.put("Rice", new String[] {"BPH", "Leaf Folder"});
        pests.put("Maize", new String[] {"Stem Borer", "Armyworm"});
        pests.put("Soybean", new String[] {"Aphids", "Japanese Beetle"});
        pests.put("Cotton", new String[] {"Bollworm", "Aphids"});
        pests.put("Barley", new String[] {"Barley Yellow Dwarf Virus", "Hessian Fly"});
        pests.put("Potato", new String[] {"Potato Beetle", "Potato Blight"});
        pests.put("Tomato", new String[] {"Tomato Hornworm", "Blossom End Rot"});
        pests.put("Carrot", new String[] {"Carrot Rust Fly", "Aphids"});
        pests.put("Peanut", new String[] {"Peanut Leaf Spot", "Root Knot Nematode"});

        String[] cropPests = pests.getOrDefault(crop, new String[] {"No data available"});
        return String.join(", ", cropPests);
    }

    public String cropRotationSuggestion(String currentCrop) {
        Map<String, String> rotationPlan = new HashMap<>();
        rotationPlan.put("Wheat", "Soybean");
        rotationPlan.put("Rice", "Maize");
        rotationPlan.put("Maize", "Wheat");
        rotationPlan.put("Soybean", "Cotton");
        rotationPlan.put("Cotton", "Maize");
        rotationPlan.put("Barley", "Peanut");
        rotationPlan.put("Potato", "Tomato");
        rotationPlan.put("Tomato", "Carrot");
        rotationPlan.put("Carrot", "Barley");
        rotationPlan.put("Peanut", "Wheat");

        return rotationPlan.getOrDefault(currentCrop, "No rotation plan available");
    }

    public String irrigationScheduleSuggestion(String crop) {
        Map<String, String> irrigationPlan = new HashMap<>();
        irrigationPlan.put("Wheat", "Irrigate every 7 days");
        irrigationPlan.put("Rice", "Irrigate every 5 days");
        irrigationPlan.put("Maize", "Irrigate every 10 days");
        irrigationPlan.put("Soybean", "Irrigate every 12 days");
        irrigationPlan.put("Cotton", "Irrigate every 14 days");
        irrigationPlan.put("Barley", "Irrigate every 9 days");
        irrigationPlan.put("Potato", "Irrigate every 6 days");
        irrigationPlan.put("Tomato", "Irrigate every 8 days");
        irrigationPlan.put("Carrot", "Irrigate every 7 days");
        irrigationPlan.put("Peanut", "Irrigate every 10 days");

        return irrigationPlan.getOrDefault(crop, "No irrigation schedule available");
    }
}

class CropConditions {
    private double minPh, maxPh;
    private int minMoisture, maxMoisture;
    private NutrientLevels nutrientRequirements;

    public CropConditions(double minPh, double maxPh, int minMoisture, int maxMoisture, NutrientLevels nutrientRequirements) {
        this.minPh = minPh;
        this.maxPh = maxPh;
        this.minMoisture = minMoisture;
        this.maxMoisture = maxMoisture;
        this.nutrientRequirements = nutrientRequirements;
    }

    public boolean isSuitable(double soilPh, int moistureLevel, NutrientLevels soilNutrients) {
        return soilPh >= minPh && soilPh <= maxPh && moistureLevel >= minMoisture && moistureLevel <= maxMoisture &&
               soilNutrients.isSufficient(nutrientRequirements);
    }
}

class NutrientLevels {
    private int nitrogen;
    private int phosphorus;
    private int potassium;

    public NutrientLevels(int nitrogen, int phosphorus, int potassium) {
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
    }

    public boolean isSufficient(NutrientLevels requiredLevels) {
        return this.nitrogen >= requiredLevels.nitrogen &&
               this.phosphorus >= requiredLevels.phosphorus &&
               this.potassium >= requiredLevels.potassium;
    }
}

public class Main {
    public static void main(String[] args) {
        CropSoilManagementSystem system = new CropSoilManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Example Inputs
        System.out.print("Enter Soil pH level: ");
        double soilPh = scanner.nextDouble();
        System.out.print("Enter Soil Moisture level (%): ");
        int moistureLevel = scanner.nextInt();

        // Example Nutrient Levels
        System.out.print("Enter Soil Nitrogen level: ");
        int nitrogen = scanner.nextInt();
        System.out.print("Enter Soil Phosphorus level: ");
        int phosphorus = scanner.nextInt();
        System.out.print("Enter Soil Potassium level: ");
        int potassium = scanner.nextInt();

        NutrientLevels soilNutrients = new NutrientLevels(nitrogen, phosphorus, potassium);

        List<String> recommendedCrops = system.getRecommendation(soilPh, moistureLevel, soilNutrients);
        if (!recommendedCrops.isEmpty()) {
            System.out.println("\nBased on the provided soil, moisture, and nutrient data, suitable crops are:");
            for (String crop : recommendedCrops) {
                System.out.println("- " + crop);

                // Example: Show pest control suggestion for each crop
                String pests = system.pestControlSuggestion(crop);
                System.out.println("  Pest control suggestions for " + crop + ": " + pests);

                // Example: Show crop rotation suggestion for each crop
                String rotationSuggestion = system.cropRotationSuggestion(crop);
                System.out.println("  Suggested crop rotation: " + rotationSuggestion);

                // Example: Show irrigation schedule suggestion for each crop
                String irrigationSuggestion = system.irrigationScheduleSuggestion(crop);
                System.out.println("  Irrigation schedule: " + irrigationSuggestion);
            }
        } else {
            System.out.println("No suitable crops found for the provided conditions.");
        }

        scanner.close();
    }
}
