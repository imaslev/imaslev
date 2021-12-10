/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.UI;

import java.util.Scanner;

/**
 *
 * @author ivaylomaslev
 */
public  class DVDLibraryConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    /**
     *
     * @param massage
     */
    @Override
    public void print(String massage) {
        System.out.println(massage);
    }

    @Override
    public double readDouble(String prompt) {

        double returnReadDouble = 0.0;
        while (true) {
            String myReadDouble;
            myReadDouble = readString(prompt);
            try {
                returnReadDouble = Double.parseDouble(myReadDouble);
                break;
            } catch (NumberFormatException e) {
                print("Plaese enter a valid double");

            }
        }

        /*https://stackoverflow.com/questions/3543729/how-to-check-that-a-string-is-parseable-to-a-double*/
        return returnReadDouble;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public double readDouble(String prompt, double min, double max) {

        double returnReadDouble = 0.0;
        while (true) {
            String myReadDouble;
            myReadDouble = readString(prompt);
            try {
                returnReadDouble = Double.parseDouble(myReadDouble);
                if (returnReadDouble < min || returnReadDouble > max) {
                    print("Please enter a number betwin " + min + " And " + max);
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                print("Plaese enter a valid double");

            }
        }

        return returnReadDouble;
    }

    @Override
    public float readFloat(String prompt) {

        float returnReadFloat = (float) 0.0;
        while (true) {
            String myReadFloat;
            myReadFloat = readString(prompt);
            try {
                returnReadFloat = Float.parseFloat(myReadFloat);
                break;
            } catch (NumberFormatException e) {

                print("Please Enter a valid Float");

            }
        }
        return returnReadFloat;

    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        float returnReadFloat = (float) 0.0;
        while (true) {
            String myReadFloat;
            myReadFloat = readString(prompt);
            try {
                returnReadFloat = Float.parseFloat(myReadFloat);
                if (returnReadFloat < min || returnReadFloat > max) {
                    print("Please enter a number betwin " + min + " And " + max);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {

                print("Please Enter a valid Float");

            }
        }
        return returnReadFloat;

    }

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public int readInt(String prompt) {

        int returnReadInteger = 0;

        while (true) {
            String myReadIntegerAsString;
            myReadIntegerAsString = readString(prompt);
            try {

                returnReadInteger = Integer.parseInt(myReadIntegerAsString);
                break;
            } catch (NumberFormatException e) {

                print("Please Enter a valid Integer");

            }
        }

        return returnReadInteger;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        int returnReadInt = 0;
        int myReadInteger = 0;

        while (true) {
            myReadInteger = readInt(prompt);
            try {
                System.out.println("int = " + myReadInteger + " min = " + min + " max = " + max);
                if (myReadInteger < min || myReadInteger > max) {
                    print("Please enter a number betwin " + min + " And " + max);
                    continue;
                }
                returnReadInt = myReadInteger;
                break;
            } catch (NumberFormatException e) {

                print("Please Enter a valid Integer");

            }
        }
        return returnReadInt;

    }

    @Override
    public long readLong(String prompt) {

        long returnReadLong = 0;

        while (true) {
            String myReadLong;
            myReadLong = readString(prompt);
            try {
                returnReadLong = Long.parseLong(myReadLong);
                break;
            } catch (NumberFormatException e) {
                print("Please enter a number");

            }
        }
        return returnReadLong;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    @Override
    public long readLong(String prompt, long min, long max) {

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

    /**
     *
     * @param prompt
     * @return
     */
    @Override
    public String readString(String prompt) {

        System.out.println(prompt);
        return sc.nextLine();

    }

    

}
