import java.util.Objects;

public class MaterialType {

    private String name;
    private String description;
    private String icon;
    private int maxCapacity; // This is for maximum capacity of this material type in a warehouse


    public MaterialType(String name, String description, String icon, int maxCapacity) {

        //For checking the input parameters for MaterialType
        if (name == null || description == null || icon == null || maxCapacity <= 0) {
            throw new IllegalArgumentException("There are invalid parameters for MaterialType");
        }
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maxCapacity = maxCapacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }



    //When we need comparing MaterialTypes,
    //it will compare them by contents of MaterialType's name, description, icon and maxCapacity,
    //if one of this is not matching they are not equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MaterialType other = (MaterialType) obj;
        return maxCapacity == other.maxCapacity &&
                Objects.equals(name, other.name) &&
                Objects.equals(description, other.description) &&
                Objects.equals(icon, other.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, icon, maxCapacity);
    }
}
