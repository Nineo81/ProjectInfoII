package Model;

import java.util.Random;

public class Level {

    int[][] map,
            directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Level(){

        Random rand = new Random();
        int dimension =20,
                maxTunnels = 70,
                maxLength = 10,
                currentRow = rand.nextInt(dimension),
                currentColumn = rand.nextInt(dimension),
                randomLength,
                tunnelLength;
        int[] lastDirection = new int[2],
                randomDirection;

        this.map = matrixGenerator(1,dimension);

        // Start of algorithm
        while(maxTunnels != 0) {

            //Check if the new direction does go back on track and if so, choose a new one
            do {
                randomDirection = directions[rand.nextInt(4)];
            } while (randomDirection[0] == -lastDirection[0] && randomDirection[1] == -lastDirection[1]);

            randomLength = rand.nextInt(maxLength);
            tunnelLength =0;

            //Constructing tunnel
            while(tunnelLength<randomLength){

                //break the loop if it is going out of the map
                if (((currentRow == 1) && (randomDirection[0] == -1)) ||
                        ((currentColumn == 1) && (randomDirection[1] == -1)) ||
                        ((currentRow == dimension - 2) && (randomDirection[0] == 1)) ||
                        ((currentColumn == dimension - 2) && (randomDirection[1] == 1))) {
                    break;
                }
                else{
                    map[currentRow][currentColumn] = 0;
                    for(int i=-1;i<=1;i++){
                        for(int j=-1;j<=1;j++){
                            if(map[currentRow+i][currentColumn+j]!=0){
                                map[currentRow+i][currentColumn+j]=2;
                            }
                        }
                    }
                    currentRow += randomDirection[0];
                    currentColumn += randomDirection[1];
                    tunnelLength++;
                }
            }

            //Prevent a tunnel without length
            if(tunnelLength != 0){
                lastDirection=randomDirection;
                maxTunnels--;
            }
        }

    }

    public int[][] matrixGenerator(int num,int dimension){
        int[][] matrix = new int[dimension][dimension];
        for (int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                matrix[i][j]=num;
            }
        }
        return matrix;
    }

    public int[][] getLevelMap(){
        return this.map;
    }
}
