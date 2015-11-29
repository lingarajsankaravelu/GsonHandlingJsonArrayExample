package com.hourglass.lingaraj.gsonhandlingjsonarrayexample;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lingaraj on 11/29/15.
 */
public class SerializableJsonDataHolder implements Serializable {

   public List<ContactDetail> contactDetailsList;


}
class ContactDetail
{
    public String name;
    public int phoneNumber;
    public String emailId;
    public String address;

}