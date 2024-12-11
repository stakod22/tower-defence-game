public class StandardTower extends Tower {
    public StandardTower(int id, Vector location) {
        super(id, location);
    }

    @Override
    public Projectile shootProjectile(){
        return null;
    }
}
