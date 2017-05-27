import java.util.Arrays;

public class Perceptron
{


public static void main(String[] args){
/*
int[][] data = {{0,4,1},
               {0,0,-1},
               {4,4,1} };

*/


//*************Input for Perceptron alg*****************
int[][] data = {{1,4,1},
               {2,3,1},
               {3,2,-1},
               {4,1,-1} };
//*******************************************************

int instances = data.length;


int atributes = 2;

int[][] currentWeights = new int[instances][3];
int[][] oldWeights = new int[instances][3];

int[] weights ={0,0,0};
int[] instance ={0,0,0};
int maxIterations = 5;
int wDOTa = 0;
int yactual =0;
for(int i = 0;i<maxIterations;i++){

  for(int j =0;j< instances ;j++){

   instance[0] =  1 ;
   instance[1] = data[j][0];
   instance[2] = data[j][1];
   yactual = data[j][2];
    wDOTa = (weights[0]*instance[0]) + (weights[1]*instance[1]) + (weights[2]*instance[2]);


    System.out.print(weights[0] + " ");
    System.out.print(weights[1] + " ");
    System.out.print(weights[2]);
    System.out.println();

    currentWeights[j][0] = weights[0];
    currentWeights[j][1] = weights[1];
    currentWeights[j][2] = weights[2];

    if( (wDOTa < 0) && (yactual == 1) ){
       weights[0]= weights[0] + (yactual*instance[0]);
       weights[1]= weights[1] + (yactual*instance[1]);
       weights[2]= weights[2] + (yactual*instance[2]);
    }

    if( (wDOTa > 0) && (yactual == -1) ){
       weights[0]= weights[0] + (yactual*instance[0]);
       weights[1]= weights[1] + (yactual*instance[1]);
       weights[2]= weights[2] + (yactual*instance[2]);
    }

    if( (wDOTa == 0)  ){
       weights[0]= weights[0] + (yactual*instance[0]);
       weights[1]= weights[1] + (yactual*instance[1]);
       weights[2]= weights[2] + (yactual*instance[2]);
    }





  }

System.out.println("--------");

if(i>0){
  if(Arrays.deepEquals(currentWeights,oldWeights)){
       break;

  }

}


oldWeights = currentWeights.clone();


}


}


}
