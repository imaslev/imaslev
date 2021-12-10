/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author ivaylomaslev
 */
public class UserIOImpl implements UserIO{
    Scanner myScanner = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);   
    }    
    
    @Override
    public double readDouble(String prompt) throws NumberFormatException{
        
        Double a = null;
        
        boolean loop = true;
        
        do{
            try{
                System.out.println(prompt);
                a = Double.parseDouble(myScanner.nextLine());
                loop = false;
            }catch(NumberFormatException e){
                
            }
        }while(loop);
        
        return a;
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) throws NumberFormatException{
        Double a = null;
        boolean loop = true;

        do{
            try{
                do{
                    System.out.println(prompt);

                    a = Double.parseDouble(myScanner.nextLine());
                    loop = false;
                    if (a > max || a< min){
                        System.out.println("Your entry was not within the specified bounds."); 
                        loop = true;
                    }

                }while(a > max || a< min);
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);

        return a;
    }
    
   
    @Override
    public float readFloat(String prompt) throws NumberFormatException{
        System.out.println(prompt);
        float a = myScanner.nextFloat();
        return a;
    }

    @Override
    public float readFloat(String prompt, float min, float max) throws NumberFormatException{
        float a;

        do{
            System.out.println(prompt);

             a= myScanner.nextFloat();

            if (a > max || a< min){
                System.out.println("Your entry was not within the specified bounds."); 
            }

        }while(a > max || a< min);

         return a;
    }
    
    @Override
    public int readInt(String prompt) throws NumberFormatException{
        int a = 0;
        boolean loop = true;
        
        do{
            try{
                System.out.println(prompt);
                a = Integer.parseInt(myScanner.nextLine());
                loop = false;
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);
        
        return a;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws NumberFormatException{
        int a = 0;
        boolean loop = true;
        
        do{
            try{
                do{
                   System.out.println(prompt);

                   a= Integer.parseInt(myScanner.nextLine());

                   if (a > max || a< min){
                       System.out.println("Your entry was not within the specified bounds."); 
                   }

                }while(a > max || a< min);
                loop = false;
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);
       

         return a;
    }
    
    @Override
    public long readLong(String prompt) throws NumberFormatException{
        System.out.println(prompt);
        long a = myScanner.nextLong();
        return a;
    }

    @Override
    public long readLong(String prompt, long min, long max) throws NumberFormatException{
       /*long a;
       

        do{
            System.out.println(prompt);

             a= myScanner.nextLong();

            if (a > max || a< min){
                System.out.println("Your entry was not within the specified bounds."); 
            }

        }while(a > max || a< min);

         return a;*/
       long readLong = 0;

        while (true) {
            String myReadLong;
            myReadLong = readString(prompt);

            try {
                long returnReadLong = Long.parseLong(myReadLong);
                if (returnReadLong < min || returnReadLong > max) {
                    print("Enter a number betwin " + min + "And" + max);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                print("Enter a valid number ");

            }
        }
        return 0;
    }
    
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        
        String a = myScanner.nextLine();
        return a;
    }
    
}
