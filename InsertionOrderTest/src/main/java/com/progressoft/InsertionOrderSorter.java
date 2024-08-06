package com.progressoft;

import java.util.*;

public class InsertionOrderSorter {
    private final Map<String, Set<String>> tables;

    public InsertionOrderSorter(Map<String, Set<String>> tables) {
        this.tables = tables;
    }

    public List<String> calculateInsertionOrder() {
        //TODO implement this function to make unit tests pass
        List<String> insertionOrderList = new ArrayList<>();
        List<String> unlinkedTables = new ArrayList<>();
        handleTableInsertion(insertionOrderList, unlinkedTables);
        insertionOrderList.addAll(0, unlinkedTables);
        return new ArrayList<>(new LinkedHashSet<>(insertionOrderList));
    }

    private void handleTableInsertion(List<String> insertionOrderList, List<String> unlinkedTables) {
        for (String tableKey : tables.keySet()) {
            List<String> foreignKeys = new ArrayList<>(tables.get(tableKey));
            if (!insertionOrderList.contains(tableKey) && foreignKeys.isEmpty())
                unlinkedTables.add(tableKey);
            else
                addLinkedTables(insertionOrderList, foreignKeys, tableKey);
        }
    }

    private void addLinkedTables(List<String> insertionOrderList, List<String> foreignKeys, String tableKey) {
        if (insertionOrderList.contains(tableKey))
            insertionOrderList.addAll(insertionOrderList.indexOf(tableKey), foreignKeys);
        else
            addForeignKeysWhenTableKeyNotAppend(insertionOrderList, foreignKeys, tableKey);
    }

    private void addForeignKeysWhenTableKeyNotAppend(List<String> insertionOrderList, List<String> foreignKeys, String tableKey) {
        for (String foreignKey : foreignKeys)
            if (!insertionOrderList.contains(foreignKey))
                insertionOrderList.add(foreignKey);
        insertionOrderList.add(tableKey);
    }
}
