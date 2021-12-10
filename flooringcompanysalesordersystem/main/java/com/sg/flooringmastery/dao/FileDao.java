/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author ivaylomaslev
 */
public  class FileDao<T>{
    private final String path;
    private final int columnCount; 
    private final boolean hasHeaders;
    protected final String HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    public FileDao(String path, int columnCount, boolean hasHeaders) {
        this.path = path;
        this.columnCount = columnCount;
        this.hasHeaders = hasHeaders;
    }

    public List<T> read(Function<String[], T> mapper) throws StorageException {

        ArrayList<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            if (hasHeaders) { 
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");
                for(int i = 0; i < tokens.length; i++){
                    tokens[i] = tokens[i].replace("|" , ",");
                }
//replace special char example "|" with ","
                if (tokens.length == columnCount) {
                    T obj = mapper.apply(tokens);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
        return result;
    }

    public void write(Collection<T> items, Function<T, String> mapper) throws StorageException {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.println(HEADER);
            for (T obj : items) {
                if (obj != null) {
                    writer.println(mapper.apply(obj));
                }
            }
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
    }

    public void append(T item, Function<T, String> mapper) throws StorageException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(mapper.apply(item));
        } catch (IOException ex) {
            throw new StorageException(ex.getMessage(), ex);
        }
    }
    
    public void appendString(String input) throws StorageException{
        try(PrintWriter writer = new PrintWriter(new FileWriter(path, true))){
            writer.println(input);
        }catch (IOException ex){
            throw new StorageException(ex.getMessage(), ex);
        }
    }
    
}
