package structs;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import forum.SecurityHandler;

public class User {
    protected String _fname;
    protected String _sname;
    protected String _username;
    protected String _password;
    protected String _email;

    public String get_fname() {
	return _fname;
    }

    public void set_fname(String _fname) {
	this._fname = _fname;
    }

    public String get_sname() {
	return _sname;
    }

    public void set_sname(String _sname) {
	this._sname = _sname;
    }

    public String get_username() {
	return _username;
    }

    public void set_username(String _username) {
	this._username = _username;
    }

    public String get_password() {
	return _password;
    }

    public void set_password(String _password) {
	this._password = SecurityHandler.generateMd5(_password);
    }

    public String get_email() {
	return _email;
    }

    public void set_email(String _email) {
	this._email = _email;
    }
}
