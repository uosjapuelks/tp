package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.util.ArrayList;

public class ItemList {
    protected ArrayList<Item> itemList;

    public ItemList() {
        this.itemList = new ArrayList<>();
    }

    /**
     * Adds an item into itemList.
     *
     * @param item The Item to be added.
     * @return Updated quantity of the item added in the list.
     */
    public int addItem(Item item) {
        assert item != null : "Item must not be null!";
        for (Item itemInList : itemList) {
            if (itemInList.getItemName().equalsIgnoreCase(item.getItemName())
                    && itemInList.getExpiryDate().equals(item.getExpiryDate())) {
                itemInList.addQuantity(1);
                return itemInList.getQuantity();
            }
        }

        itemList.add(item);
        return 1;
    }

    /**
     * Removes an item from the item list.
     *
     * @param item Item to be removed.
     * @param qty  Quantity to be removed.
     * @return Returns true if item is removed from the list (qty = 0)
     */
    public boolean removeItem(Item item, int qty) {
        assert item != null : "Item must not be null!";
        assert qty != 0 : "Quantity removed must not be 0!";
        if (item.getQuantity() == qty) {
            itemList.remove(item);
            return !searchItemNameExist(item);
        }
        item.removeQuantity(qty);
        return !searchItemNameExist(item);
    }

    /**
     * Checks if an item with the same name exists in the item list.
     *
     * @param item Item to be searched.
     * @return Boolean value of true if exist.
     */
    public boolean searchItemNameExist(Item item) {
        for (Item itemInList : itemList) {
            if (itemInList.getItemName().equalsIgnoreCase(item.getItemName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Overwrite the quantity of an item to the new amount.
     *
     * @param targetItem Item in which quantity is to be overwritten.
     * @param toQuantity Overwrite to this quantity.
     */
    public void updateQuantity(Item targetItem, int toQuantity) {
        targetItem.setQuantity(toQuantity);
    }

    /**
     * Checks if the itemList contains an item.
     *
     * @param item Item we want to check if it exists in the itemList.
     * @return true when itemList contains item and false when itemList does not contain item.
     */
    public boolean containsItem(Item item) {
        assert item != null : "Item must not be null!";
        return itemList.contains(item);
    }

    /**
     * Sorts the items in itemList by date if byDate is true and by description if false.
     *
     * @param byDate toggle to sort by name or expiry.
     * @return sorted item ArrayList.
     * @throws FridgetException thrown when there are no items in itemList.
     */
    public ArrayList<Item> sortItem(boolean byDate) throws FridgetException {
        if (itemList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your fridge.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Item> sortedList = new ArrayList<Item>(itemList);
        sortedList.sort((byDate ? Item.ItemExpiryComparator : Item.ItemNameComparator));
        return sortedList;
    }

    /**
     * Returns the current list of items.
     *
     * @return List of items.
     * @throws FridgetException thrown when String after list is unrecognised.
     */
    public ArrayList<Item> getItemList(String sortType) throws FridgetException {
        assert sortType != null : "Sort type must not be null!";
        switch (sortType.toLowerCase()) {
        case "e":
            return sortItem(true);
        case "default":
            return sortItem(false);
        case "r":
            return itemList;
        default:
            throw new FridgetException("Unrecognisable list command. Try: <list -e>");
        }
    }

    /**
     * Returns an Array List of Items with names containing the search term.
     *
     * @param searchTerm The search term used to find matching Items.
     * @throws FridgetException thrown when there are no items in itemList.
     */
    public ArrayList<Item> findAllMatchingItems(String searchTerm) throws FridgetException {
        assert searchTerm != null : "Search term must not be null!";
        if (itemList.isEmpty()) {
            String emptyListMessage = "You currently have nothing in your fridge.\n"
                    + "Input \"help\" to get started!";
            throw new FridgetException(emptyListMessage);
        }
        ArrayList<Item> matchingItems = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getItemName().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    /**
     * Resets the Array List of items.
     */
    public void resetList() {
        itemList = new ArrayList<>();
    }
}
