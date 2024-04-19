public class Material {
    private MaterialType type;
    private int quantity;


    public Material(MaterialType type, int quantity) {
        if (type == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid material type or quantity");
        }
        this.type = type;
        this.quantity = quantity;
    }

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid materialType");
        }
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Invalid quantity: quantity cannot be negative");
        }
        this.quantity = quantity;
    }

}
