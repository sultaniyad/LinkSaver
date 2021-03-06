package com.iyad.sultan.linksaver.Model;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sultan on 11/16/16.
 */

public class RModel {

    private final Realm realm;
    private RealmResults<Link> results;

    public RModel() {
        realm = Realm.getDefaultInstance();
    }

    //important link
    public RealmResults<Link> getLinksByImportant() {

        return results=realm.where(Link.class).equalTo("isImportant", true).findAll();
    }
    //search
    public RealmResults<Link> getLinksBySearch(String key) {

        if(key.isEmpty())
            return getLinksAll();
        return results=realm.where(Link.class).contains("Title", key, Case.INSENSITIVE).findAll();
    }

//get all links
    public RealmResults<Link> getLinksAll() {
        return results= realm.where(Link.class).findAllSorted("date", Sort.DESCENDING);

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
    public void deleteLink(final int position) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               results.get(position).deleteFromRealm();

            }
        });

    }

    public void removeRealm(){
        if(realm !=null)
            realm.close();
    }


    public void chnageStatus(final int position){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final  Link l = results.get(position);
                l.setImportant(!l.isImportant());
            }
        });
    }


}
