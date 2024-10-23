import static java.lang.Math.*;

public class Main {
    public static final short[] z = new short[10];
    public static final float[] x = new float[12];
    public static double e = 2.71828182845904;

    public static void main(String[] args){
        int c = 0; 
        for (int i = 23; i >= 5; i-=2){
            z[c] = (short)i;
            c += 1;
        }
        
        for (int i = 0; i < 12; ++i){
            x[i] = getRandomValue();
        }
        workWithArray(z, x);
    }

    public static float getRandomValue(){
        float value = (float)random()*17-2;
        return value;
    }

    public static void workWithArray(short[] firstz, float[] firstx){
        float x;
        float[][] z1;
        z1 = new float[10][12];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 12; j++){
                x = firstx[j];
				switch(firstz[i]){
					case(13): z1[i][j] = (float)((pow(sin(x),  1.0/3) + 1)/2);
                    break;
					case(11): 
                    case(15): 
                    case(17):  
                    case(21): 
                    case(23):  z1[i][j] = (float)(asin(1/pow(e, (pow(sin(pow((asin((x + 6.5)/17)), (asin((x + 6.5)/17))/2)), 2))))); break;
					default: z1[i][j] = (float)pow(e, cos(pow(e, pow(e, x)))); 
						break;
				}
                
            }
        }
        printArray(z1);
    }
    
    public static void printArray(float[][] array){ 
        for (int i = 0; i < 10; i++){
            System.out.println("    ");
            for (int j = 0; j < 12; ++j){
                double answer = array[i][j];
                if (Double.isNaN(answer)){
                    System.out.print(" |");
                    System.out.print("NaN");
                    System.out.print(" |     ");
                }
                else{
                    System.out.print(" |");
                    System.out.format("%.2f", answer);
                   
                    System.out.print("|     ");
                }      
            }   
        } 
    }
}
