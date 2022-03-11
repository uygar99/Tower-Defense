package Source;

public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    /**
     *
     * @param position
     * @param direction
     * @return Vector2D
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        Vector2D vector2D = null;
        if(position.getY() == 30 && position.getX() < 330)
        {
            int xi = (int) (position.getX() +1);
            vector2D= new Vector2D(xi,position.getY());
        }
        else if(position.getX() == 330 && position.getY() < 330)
        {
            int xi = (int) (position.getY()+1);
            vector2D= new Vector2D(xi,position.getY());
        }
        else if(position.getY() == 330 && position.getX() > 30)
        {
            int xi = (int) (position.getX() -1);
            vector2D= new Vector2D(xi,position.getY());
        }
        else if(position.getX() <= Commons.StartWidth && position.getY() > 30)
        {
            int xi = (int) (position.getY()-1);
            vector2D= new Vector2D(xi,position.getY());
        }

        return vector2D;
    }
}
