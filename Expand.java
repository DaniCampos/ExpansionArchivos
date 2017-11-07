import java.io.*;
import java.util.*;

/*
@version 24-04-2017
*/

public class Expand {

    public static int check(String archivo, ListaEnlazada archivos){
        String line;

        int b = 0;

        try{

            FileReader readableFile = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(readableFile);
            line = reader.readLine();
            while(line!=null){
                
                int a = 0;
                boolean hayArchivo = false;
                for(int i = 0; i < line.length(); i++){

                    if(a > 0){
                        a--;
                        continue;
                    }

                    if(line.charAt(i) == '<'){
                        a = 2;
                        hayArchivo = true;
                        int j;

                        for(j = 0; j < line.length(); j++){
                            if(line.charAt(j) == '>'){
                                break;
                            }
                        }

                        String cortar = "<<<" + line.substring(i + 3, j)
                            + ">>>";
                        String nuevoArchivo = line.substring(i + 3, j);
                        char endline = line.charAt(line.length() - 1);
                        if(archivos.contains(nuevoArchivo + ".txt") != -1){
                            b = -1;
                        }
                        else{
                            ListaEnlazada l = new ListaEnlazada
                                (nuevoArchivo + ".txt");
                            archivos.concatenate(l);
                            line = line.replace(cortar, "");
                            b = check(nuevoArchivo + ".txt", archivos);
                        }
                    }
                }
            
                line = reader.readLine();
            }

            reader.close();
            readableFile.close();

        }

        catch(IOException e){
            e.printStackTrace();
        }
        return b;
    }



    public static void lector(String archivo){
        String line;

        try{

            FileReader readableFile = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(readableFile);
            line = reader.readLine();

            while(line != null){
                
                int a = 0;
                boolean hayArchivo = false;
                for(int i = 0; i < line.length(); i++){

                    if(a > 0){
                        a--;
                        continue;
                    }

                    if(line.charAt(i) == '<'){
                        a = 2;
                        hayArchivo = true;
                        int j;

                        for(j = 0; j < line.length(); j++){
                            if(line.charAt(j) == '>'){
                                break;
                            }
                        }

                        String cortar = "<<<" + line.substring(i + 3, j)
                            + ">>>";
                        String nuevoArchivo = line.substring(i + 3, j);
                        char endline = line.charAt(line.length() - 1);
                        
                        
                        line = line.replace(cortar, "");

                        if(endline == '>'){
                            System.out.print(line);
                            lector(nuevoArchivo + ".txt");
                        }
                        else {
                            lector(nuevoArchivo + ".txt");
                            System.out.println(line);
                        }
                        
                    }
                }
                
                if(hayArchivo == false){
                    System.out.println(line);   
                }
                line = reader.readLine();
            }

            reader.close();
            readableFile.close();

            if(line != null){
                System.out.println(line);
            }

        }

        catch(IOException e){
            e.printStackTrace();
        }

    }

	public static void main(String[] args){

        for (String s: args) {
            ListaEnlazada archivos = new ListaEnlazada(s);
            if (check(s, archivos) == 0){
                lector(s);    
            }
            else {
                System.out.println("Se ha encontrado una referencia circular en " + s);
            }
            
        }
	}


}