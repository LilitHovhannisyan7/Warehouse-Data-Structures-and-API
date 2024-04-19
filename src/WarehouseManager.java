import java.util.ArrayList;
import java.util.List;


public class WarehouseManager {
    private List<Warehouse> warehouses;
    private WarehouseObserver observer;

    public WarehouseManager() {
        warehouses = new ArrayList<>();
        observer = new WarehouseUpdateChecker();
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    // This method is for setting observer to follow the manager's warehouses
    public void setObserver(WarehouseObserver observer) {
        this.observer = observer;
    }

    // This method adds a warehouse to the manager to manage and returns index of that WareHouse
    public int addWarehouse(Warehouse warehouse) {
        if (warehouse == null) {
            throw new IllegalArgumentException("Warehouse cannot be null");
        }

        warehouse.addObserver(observer);
        warehouses.add(warehouse);
        return warehouses.indexOf(warehouse);
    }

    // This method removes a warehouse from the manager
    public void removeWarehouse(Warehouse warehouse) {
        if (!warehouses.contains(warehouse)) {
            throw new IllegalArgumentException("Manager does not manage mentioned Warehouse");
        }
        warehouse.removeObserver(observer);
        warehouses.remove(warehouse);
    }


    // This method gets the warehouse at the mentioned index
    public Warehouse getWarehouse(int index) {
        if (index >= 0 && index < warehouses.size()) {
            return warehouses.get(index);
        }
        throw new IllegalArgumentException("Invalid index");
    }

    // This method gets a specific warehouse object
    public Warehouse getWarehouse(Warehouse warehouse) {
        if (warehouses.contains(warehouse)) {
            return warehouse;
        }
        throw new IllegalArgumentException("Manager does not manage mentioned Warehouse");
    }


    // This method prints data of all managed warehouses by the manager
    public void printAllWarehousesData() {
        System.out.println("\nAll Warehouses's Data: ");
        for (Warehouse warehouse : warehouses) {
            System.out.println("\nWarehouse: " + warehouses.indexOf(warehouse) + ", Max Capacity = " + warehouse.getMaxCapacity());
            warehouse.printWarehouseData();
            System.out.println();
        }
    }
}