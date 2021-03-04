package com.mdsdanny.display.main;

import com.google.gson.Gson;
import com.mdsdanny.commons.Calls;
import com.mdsdanny.commons.Crypto;
import com.mdsdanny.commons.StoreConfigAccessException;
import com.mdsdanny.commons.dtos.DisplayPermission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *
 *
 */
@Controller
public class PermissionsController extends MainController {

    private static final Logger LOGGER = Logger.getLogger(PermissionsController.class.getName());

    @RequestMapping(value = "/")
    public String home() {
        return "permissions";
    }

    /**
     * By request call given a league code. Imports into our app the specific league.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/permissions/list-permissions", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> listPermissions() throws StoreConfigAccessException, IOException, InterruptedException {
        HttpStatus resultCode = HttpStatus.OK;
        String resultStr = "";
        ArrayList<DisplayPermission> permissions = new ArrayList<DisplayPermission>();
        try {
            String listPermissionsUrl = getEnv().getProperty("urls.gateway") + "/list-permissions";
            ArrayList result = (ArrayList)
                    Calls.doGet(null, URI.create(listPermissionsUrl), ArrayList.class, false);

            if(result != null) {
                for (Object p : result) {
                    if (p instanceof String) {
                        try {
                            String s = new Crypto("Permissions").getDecrypted(((String) p));
                            DisplayPermission dp = new Gson().fromJson(s, DisplayPermission.class);
                            if (dp != null) {
                                permissions.add(dp);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            permissions = null;
                        }
                    }
                }
            }
        } catch(StoreConfigAccessException e){
            if(e.getStatus() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
                resultStr = "There was an error listing permissions";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_NOT_FOUND){
                resultCode = HttpStatus.NOT_FOUND;
                resultStr = "No permissions found";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_CONFLICT){
                resultCode = HttpStatus.CONFLICT;
                resultStr = "No results listing permissions";
                LOGGER.severe(resultStr);
            }
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(ConnectException e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(Exception e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        }
        return new ResponseEntity<Object>(permissions, resultCode);
    }

    /**
     * By request call given a league code. Imports into our app the specific league.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/permissions/add-permission", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> addPermission(@RequestBody Object permissionBody) throws StoreConfigAccessException, IOException, InterruptedException {
        HttpStatus resultCode = HttpStatus.OK;
        String resultStr = "";
        DisplayPermission permission = null;
        Object p = null;
        try {
            DisplayPermission dp = (new Gson()).fromJson((String) Optional.ofNullable(permissionBody.toString()).orElseThrow(() -> {
                return new StoreConfigAccessException(500, "Internal Server Error");
            }), DisplayPermission.class);

            String s = new Crypto("Permissions").getEncrypted(new Gson().toJson(dp));

            if(dp.getName() != null && !dp.getName().equals("") && dp.getAnotherName() != null && !dp.getAnotherName().equals("")) {
                dp.setName(URLDecoder.decode(dp.getName(), "UTF-8"));
                dp.setAnotherName(URLDecoder.decode(dp.getAnotherName(), "UTF-8"));
                String addPermissionsUrl = getEnv().getProperty("urls.gateway") + "/save-permission";
                p = (Object)
                        Calls.doPostText(null, URI.create(addPermissionsUrl), s, Object.class, false);
                s = new Crypto("Permissions").getDecrypted(p.toString());
                permission = new Gson().fromJson(s, DisplayPermission.class);
            }

        } catch(StoreConfigAccessException e){
            if(e.getStatus() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
                resultStr = "There was an error listing permissions";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_NOT_FOUND){
                resultCode = HttpStatus.NOT_FOUND;
                resultStr = "No permissions found";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_CONFLICT){
                resultCode = HttpStatus.CONFLICT;
                resultStr = "No results listing permissions";
                LOGGER.severe(resultStr);
            }
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(ConnectException e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(Exception e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        }
        return new ResponseEntity<Object>(permission, resultCode);
    }

    /**
     * By request call given a league code. Imports into our app the specific league.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/permissions/delete-permission", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> deletePermission(@RequestBody Object permissionBody) throws StoreConfigAccessException, IOException, InterruptedException {
        HttpStatus resultCode = HttpStatus.OK;
        String resultStr = "";
        DisplayPermission permission = null;
        Object p = null;
        try {
            DisplayPermission dp = (new Gson()).fromJson((String) Optional.ofNullable(permissionBody.toString()).orElseThrow(() -> {
                return new StoreConfigAccessException(500, "Internal Server Error");
            }), DisplayPermission.class);
            String s = new Crypto("Permissions").getEncrypted(new Gson().toJson(dp));

            if(dp.getName() != null && !dp.getName().equals("")) {
                dp.setName(URLDecoder.decode(dp.getName(), "UTF-8"));
                if(dp.getId() != null) {
                    String deletePermissionsUrl = getEnv().getProperty("urls.gateway") + "/delete-permission";
                    p = (Object)
                            Calls.doPostText(null, URI.create(deletePermissionsUrl), s, Object.class, false);

                    s = new Crypto("Permissions").getDecrypted(p.toString());
                    permission = new Gson().fromJson(s, DisplayPermission.class);
                }
            }

        } catch(StoreConfigAccessException e){
            if(e.getStatus() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
                resultStr = "There was an error listing permissions";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_NOT_FOUND){
                resultCode = HttpStatus.NOT_FOUND;
                resultStr = "No permissions found";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_CONFLICT){
                resultCode = HttpStatus.CONFLICT;
                resultStr = "No results listing permissions";
                LOGGER.severe(resultStr);
            }
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(ConnectException e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(Exception e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        }
        return new ResponseEntity<Object>(permission, resultCode);
    }


    /**
     * By request call given a league code. Imports into our app the specific league.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/permissions/update-permission", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> updatePermission(@RequestBody Object permissionBody) throws StoreConfigAccessException, IOException, InterruptedException {
        HttpStatus resultCode = HttpStatus.OK;
        String resultStr = "";
        DisplayPermission permission = null;
        Object p = null;
        try {
            DisplayPermission dp = (new Gson()).fromJson((String) Optional.ofNullable(permissionBody.toString()).orElseThrow(() -> {
                return new StoreConfigAccessException(500, "Internal Server Error");
            }), DisplayPermission.class);

            String s = new Crypto("Permissions").getEncrypted(new Gson().toJson(dp));

            if(dp.getName() != null && !dp.getName().equals("") && dp.getAnotherName() != null && !dp.getAnotherName().equals("")) {
                dp.setName(URLDecoder.decode(dp.getName(), "UTF-8"));
                dp.setAnotherName(URLDecoder.decode(dp.getAnotherName(), "UTF-8"));
                if(dp.getId() != null) {
                    String deletePermissionsUrl = getEnv().getProperty("urls.gateway") + "/save-permission";
                    p = (Object)
                            Calls.doPostText(null, URI.create(deletePermissionsUrl), s, String.class, false);
                    s = new Crypto("Permissions").getDecrypted(p.toString());
                    permission = new Gson().fromJson(s, DisplayPermission.class);
                }
            }

        } catch(StoreConfigAccessException e){
            if(e.getStatus() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
                resultStr = "There was an error listing permissions";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_NOT_FOUND){
                resultCode = HttpStatus.NOT_FOUND;
                resultStr = "No permissions found";
                LOGGER.severe(resultStr);
            }else if(e.getStatus() == HttpURLConnection.HTTP_CONFLICT){
                resultCode = HttpStatus.CONFLICT;
                resultStr = "No results listing permissions";
                LOGGER.severe(resultStr);
            }
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(ConnectException e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        } catch(Exception e){
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR;
            resultStr = "Connection Exception";
            LOGGER.severe(resultStr);
            return new ResponseEntity<Object>(resultStr, resultCode);
        }
        return new ResponseEntity<Object>(permission, resultCode);
    }

}
