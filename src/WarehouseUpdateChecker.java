

//This is a small implementation of WarehouseObserver for logging when
// there will be any change in Warehouse, for example: adding MaterialType

public class WarehouseUpdateChecker implements WarehouseObserver {
    @Override
    public void update(Warehouse warehouse) {
        System.out.println("Warehouse data updated. Printing new data...");
        warehouse.printWarehouseData();
    }
}