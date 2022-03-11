package Source;

public class TowerIceFactory implements ITowerFactory {

    //TODO
    @Override
    public Tower createTower(Vector2D position) {
        Tower towerIce = new TowerIce();
        return towerIce;
    }
}
