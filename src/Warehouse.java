import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Warehouse {
    private Map<MaterialType, Integer> materials;   // We use this to store the materials in the warehouse,
                                                    // the key of the map is the MaterialType,
                                                    // and the value is the quantity of that in warehouse

    private int maxCapacity; //This shows the max count of distinct MaterialTypes that can be in WareHouse
    private List<WarehouseObserver> observers;


    public Warehouse(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Invalid max capacity for warehouse");
        }
        this.maxCapacity = maxCapacity;
        materials = new HashMap<>();
        observers = new ArrayList<>();
    }



    public Map<MaterialType, Integer> getMaterials() {
        return materials;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }



    // This method add observer to the list
    public void addObserver(WarehouseObserver observer) {
        observers.add(observer);
    }

    // This method removes observer from the list
    public void removeObserver(WarehouseObserver observer) {
        observers.remove(observer);
    }

    // This method notifies all observers when there is a change in warehouse data
    private void notifyObservers() {
        for (WarehouseObserver observer : observers) {
            observer.update(this);
        }
    }



    // This method adds material to the warehouse
    public void addMaterial(Material material) {

        MaterialType materialType = material.getType();
        int quantity = material.getQuantity();

        int currentQuantityOfMaterialType = materials.getOrDefault(materialType, 0); // Get current quantity or 0 if not present

        // Checking for there is any place for Material Type in Warehouse or not
        if(currentQuantityOfMaterialType == 0 && isWareHouseFull()) {

            throw new IllegalStateException("In Warehouse there is not a place to add material");
        }

        // Checking for materialType is full of its maxCapacity or not
        if(materialType.getMaxCapacity() == currentQuantityOfMaterialType) {
            throw new IllegalStateException("Adding material exceeds materialType's max capacity");
        }

        int newQuantityForMaterialType = currentQuantityOfMaterialType + quantity;
        // If newQuantityForMaterialType is more than maxCapacity of MaterialType we add as much as possible
        if (newQuantityForMaterialType > materialType.getMaxCapacity()) {
            materials.put(materialType, materialType.getMaxCapacity());
            System.out.println("Partial addition: Only " + (materialType.getMaxCapacity() - currentQuantityOfMaterialType) + " units of " + materialType.getName() +
                    " were added, " + (quantity - (materialType.getMaxCapacity() - currentQuantityOfMaterialType)) +
                    " units were not added due to Material Type capacity limitations.");
        }
        else
        {
            materials.put(materialType, newQuantityForMaterialType);
        }
        notifyObservers();
    }


    // This method removes material from the warehouse
    public void removeMaterial(Material material) {
        MaterialType materialType = material.getType();
        int quantityRemove = material.getQuantity();

        if (!materials.containsKey(materialType)) {
            throw new IllegalArgumentException("Mentioned material is not found");
        }

        int quantity = materials.get(materialType);
        if (quantity < quantityRemove) {
            throw new IllegalStateException("Not enough material is found to remove");
        }

        if (quantity == quantityRemove) {
            materials.remove(materialType);
        } else {
            materials.put(materialType, quantity - quantityRemove);
        }
        notifyObservers();
    }

    // This method moves material from this warehouse to another warehouse
    public void moveMaterial(Material material, Warehouse dest) {

        MaterialType materialType = material.getType();
        int quantityMove = material.getQuantity();

        int currentQuantity = materials.getOrDefault(materialType, 0);
        int currentQuantityOfDest = dest.getMaterialQuantity(materialType);


        // Checking for there is enough material to move or not
        if (currentQuantity < quantityMove) {
            throw new IllegalStateException("Not enough material is found to move");
        }

        // Checking for there is any place for Material Type in Destination Warehouse or not
        if(currentQuantityOfDest == 0 && dest.isWareHouseFull()) {
            throw new IllegalStateException("moving material exceeds destination warehouse's max capacity");
        }

        // Checking for materialType is full of its maxCapacity or not
        if(materialType.getMaxCapacity() == currentQuantityOfDest) {
            throw new IllegalStateException("Adding material exceeds materialType's max capacity");
        }

        int newQuantity = currentQuantityOfDest + quantityMove;

        // If newQuantity is more than maxCapacity of MaterialType we add as much as possible
        if (newQuantity > materialType.getMaxCapacity()) {
            dest.materials.put(materialType, materialType.getMaxCapacity());
            System.out.println("Partial addition: Only " + (materialType.getMaxCapacity() - currentQuantityOfDest) + " units of " + materialType.getName() +
                    " were added, " + (quantityMove - (materialType.getMaxCapacity() - currentQuantityOfDest)) +
                    " units were not added due to Material Type capacity limitations.");
        }
        else
        {
            dest.materials.put(materialType, newQuantity);
        }

        materials.put(materialType, currentQuantity - quantityMove);

        notifyObservers();
    }

    // This method is for getting quantity of a  material type
    public int getMaterialQuantity(MaterialType materialType) {
        return materials.getOrDefault(materialType, 0);
    }

    // This method is for checking is WareHouse full or not(so is all places for Material Types are already occupied or not)
    private boolean isWareHouseFull() {
        if(materials.size() == maxCapacity) {
            return true;
        }
        return false;
    }

    // This method prints current data of the warehouse
    public void printWarehouseData() {
        System.out.println("Warehouse Data: ");
        for (Map.Entry<MaterialType, Integer> entry : materials.entrySet()) {
            MaterialType materialType = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("\nName: " + materialType.getName() +
                    "\nDescription: " + materialType.getDescription() +
                    "\nIcon: " + materialType.getIcon() +
                    "\nMaxCapacity: " + materialType.getMaxCapacity() +
                    "\nQuantity in Warehouse: " + quantity);
        }
    }
}