package pacman;

public class Pacman extends Component implements Move{
    public Pacman(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    @Override
    public void move(char direction){
        switch (direction) {
            case 'L':
                if(isValid(x-speed,y) && isValid(x-speed, y+cellSize-5)){
                    x-=speed;
                }
                else if(y > 178 && y < 182 && x == 20){
                    x=380;
                }
                index = 0;
                break;
            case 'R':
                if(isValid(x+cellSize,y) && isValid(x+cellSize, y+cellSize-5)){
                    x+=speed;
                }
                else if(y > 178 && y < 182 && x == 380){
                    x=20;
                }
                index = 1;
                break;
            case 'U':
                if(isValid(x,y-speed) && isValid(x+cellSize-5, y-speed)){
                    y-=speed;
                }
                index = 2;
                break;
            case 'D':
                if(isValid(x,y+cellSize) && isValid(x+cellSize-5, y+cellSize)){
                    y+=speed;
                }
                index = 3;
                break;

        }
    }

    @Override
    public void move() {
        
    }

}
