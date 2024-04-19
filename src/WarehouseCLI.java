import java.util.Scanner;

// Also added a small CLI (Command Line Interface) for testing the project
public class WarehouseCLI {

    private static WarehouseManager manager = new WarehouseManager();
    private static Scanner scanner = new Scanner(System.in);


    public static void start() {

        WarehouseObserver checker = new WarehouseUpdateChecker();
        manager.setObserver(checker);

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add Warehouse");
            System.out.println("2. Add Material to Warehouse");
            System.out.println("3. Remove Material from Warehouse");
            System.out.println("4. Move Material from one Warehouse to another");
            System.out.println("5. Remove Warehouse");
            System.out.println("6. Print All Warehouses Data");
            System.out.println("7. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addWarehouse();
                    break;
                case 2:
                    addMaterialToWarehouse();
                    break;
                case 3:
                    removeMaterialFromWarehouse();
                    break;
                case 4:
                    moveWarehouse();
                    break;
                case 5:
                    removeWarehouse();
                    break;
                case 6:
                    printAllWarehousesData();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addWarehouse() {
        try {
            System.out.print("Please enter max capacity for warehouse: ");
            int maxCapacity = scanner.nextInt();
            scanner.nextLine();

            Warehouse warehouse = new Warehouse(maxCapacity);
            int index = manager.addWarehouse(warehouse);
            System.out.println("Warehouse added successfully and index is : " + index);
        } catch (Exception e) {
            System.out.println("It is impossible to add warehouse for this reason: " + e.getMessage());
            System.out.println("Please try again");
        }
    }
    private static void addMaterialToWarehouse() {
        try {
            System.out.print("Please enter index of the warehouse: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            Warehouse warehouse = manager.getWarehouse(index);

            System.out.println("Please enter all details of material");
            System.out.print("Please enter material name: ");
            String name = scanner.nextLine();

            System.out.print("Please enter material description: ");
            String description = scanner.nextLine();

            System.out.print("Please enter material icon: ");
            String icon = scanner.nextLine();

            System.out.print("Please enter max capacity: ");
            int maxCapacity = scanner.nextInt();
            scanner.nextLine();

            MaterialType materialType = new MaterialType(name, description, icon, maxCapacity);

            System.out.print("Please enter quantity of material to add: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            Material material = new Material(materialType, quantity);

            warehouse.addMaterial(material);
            System.out.println("Material added to warehouse successfully");
        } catch (Exception e) {
            System.out.println("It is impossible to add material to warehouse for this reason: " + e.getMessage());
            System.out.println("Please try again");
        }
    }

    private static void removeMaterialFromWarehouse() {
        try {
            System.out.print("Please enter index of the warehouse: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            Warehouse warehouse = manager.getWarehouse(index);

            System.out.println("Please enter all details of material");
            System.out.print("Please enter material name: ");
            String name = scanner.nextLine();

            System.out.print("Please enter material description: ");
            String description = scanner.nextLine();

            System.out.print("Please enter material icon: ");
            String icon = scanner.nextLine();

            System.out.print("Please enter material maxCapacity: ");
            int maxCapacity = scanner.nextInt();

            MaterialType materialType = new MaterialType(name, description, icon, maxCapacity);

            System.out.print("Please enter quantity of material to remove: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            Material material = new Material(materialType, quantity);

            warehouse.removeMaterial(material);
            System.out.println("Material removed from warehouse successfully");
        } catch (Exception e) {
            System.out.println("It is impossible to remove material from warehouse for this reason: " + e.getMessage());
            System.out.println("Please try again");
        }
    }


    private static void moveWarehouse() {
        try {
            System.out.print("Please enter index of the source warehouse: ");
            int sourceIndex = scanner.nextInt();
            scanner.nextLine();

            Warehouse sourceWarehouse = manager.getWarehouse(sourceIndex);

            System.out.println("Please enter all details of material");
            System.out.print("Please enter index of the destination warehouse: ");
            int destIndex = scanner.nextInt();
            scanner.nextLine();

            Warehouse destWarehouse = manager.getWarehouse(destIndex);

            System.out.print("Please enter material name: ");
            String name = scanner.nextLine();

            System.out.print("Please enter material description: ");
            String description = scanner.nextLine();

            System.out.print("Please enter material icon: ");
            String icon = scanner.nextLine();

            System.out.print("Please enter material maxCapacity: ");
            int maxCapacity = scanner.nextInt();

            MaterialType materialType = new MaterialType(name, description, icon, maxCapacity);

            System.out.print("Please enter quantity of material to move: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            Material material = new Material(materialType, quantity);

            sourceWarehouse.moveMaterial(material, destWarehouse);
            System.out.println("Material moved successfully");
        } catch (Exception e) {
            System.out.println("It is impossible to move material for this reason: " + e.getMessage());
            System.out.println("Please try again");
        }
    }


    private static void removeWarehouse() {
        try {
            System.out.print("Please enter index of the warehouse to remove: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            Warehouse warehouse = manager.getWarehouse(index);
            manager.removeWarehouse(warehouse);
            System.out.println("Warehouse removed successfully");
        } catch (Exception e) {
            System.out.println("It is impossible to remove warehouse for this reason: " + e.getMessage());
            System.out.println("Please try again");
        }
    }

    private static void printAllWarehousesData() {
        manager.printAllWarehousesData();
    }

}
