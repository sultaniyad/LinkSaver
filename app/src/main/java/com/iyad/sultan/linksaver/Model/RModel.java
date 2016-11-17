package com.iyad.sultan.linksaver.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sultan on 11/16/16.
 */

public class RModel {

    private Realm realm;

    public RModel() {
        realm = Realm.getDefaultInstance();
    }

    //Get
    public List<Link> getLinksByCategory(int category) {

        return realm.where(Link.class).equalTo("Category", category).findAll();
    }

    public List<Link> getLinksByImportant() {

        return realm.where(Link.class).equalTo("isImportant", true).findAll();
    }

    public RealmResults<Link> getLinksAll() {
        return realm.where(Link.class).findAllSorted("date", Sort.DESCENDING);

    }

    //Insert
    public boolean insertLink(final Link link) {

        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insert(link);
                }
            });
            return  true;
        } catch (Exception e) {
            return false;
        }

    }

    //Delete (may not work due to many realm result)
    public boolean deleteLink(final RealmResults<Link> results, final int position) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               results.get(position).deleteFromRealm();
            }
        });
        return true;
    }

    public void removeRealm(){
        if(realm !=null)
            realm.close();
    }

    public void DumbData(){
        Calendar c=Calendar.getInstance();
        for (int x=0 ; x<10;x++){
           final   Link l=new Link();
            l.setDate(c.getTime());
            l.setTitle("Title " + x);
            l.setImportant(false);
            l.setCategory(5);
            l.setLink("Link " +x);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insert(l);
                }
            });

        }
    }
}
