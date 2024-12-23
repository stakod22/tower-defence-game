package game;

import java.util.ArrayList;
import java.util.List;

public class ProjectileList {
    private List<Projectile> projectileList = new ArrayList<>();

    public void addProjectile(Projectile projectile){
        projectileList.add(projectile);
    }


    public List<Projectile> getProjectileList() {
        return projectileList;
    }

    public void setProjectileList(List<Projectile> projectileList) {
        this.projectileList = projectileList;
    }
}
