curl -i http://localhost:8090/greenvegauth/api/token/validate

# CURL COMMAND FOR TOKEN (GRANT_TYPE= password)
curl -XPOST -i -H "Authorization: Basic dGFsazJhbWFyZXN3YXJhbjp0YWxrMmFtYXJlc3dhcmFuQDEyMw" "http://localhost:8090/greenvegauth/oauth/token?username=john@gmail.com&password=password&grant_type=password"

# CURL COMMAND FOR TOKEN (GRANT_TYPE= refresh_token)
curl -XPOST -i -H "Authorization: Basic dGFsazJhbWFyZXN3YXJhbjp0YWxrMmFtYXJlc3dhcmFuQDEyMw" "http://localhost:8090/greenvegauth/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huQGdtYWlsLmNvbSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIyODkwYjIyNy0wMzhmLTRjNGYtYjFkYy02OTdlYTQ5ZmUyNzMiLCJleHAiOjE1OTIxNTE2OTUsInVzZXJOYW1lIjoiam9obkBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX0NSRUFURV9OT1RFIiwiUk9MRV9WSUVXX05PVEUiLCJST0xFX0VESVRfTk9URSIsIlJPTEVfREVMRVRFX05PVEUiLCJST0xFX1ZJRVdfQUxMX05PVEUiXSwianRpIjoiZDUxMzI2MTQtZGVjYi00MDQ0LTgxNzEtYmI0NjRlMWFhZjVmIiwiY2xpZW50X2lkIjoidGFsazJhbWFyZXN3YXJhbiJ9.aZx1O45ZQOnaBJ_KfyYSfXdcjsC7SQVwsa6Pa-EZuSGsAry2d3nm1TM-uYPIAE-8rynFpN6tAi1QyK76sjypdymSFFimhUVFEQNHs_MavW3cEgWANpJp1s-T7cUwkEgFflthFCAaaHQuMy13NEdjO7kY8nr5HcLXlrlboSLqqvfkhU3V1DIqBh9sLJlkc8uOoFOU1TtefaZLKqVSjr2VLKeR_ZpsltYn-T0vOAFbk8V-Um8iU2sZI_GaGDlYscUilVxO2Bwg-1revQEiTV7-Q-dxq7W-DKWiJkyflRaqILVDiua_G0dPehxQ_Ohmq8FlTtf0-IiODB3S5b3ft2e2LQ"

-------------

-------  REGISTRAION----------------

http://localhost:8090/greenvegauth/api/user/register

{
"id":"0"
"firstName":"Monalisa",
"lastName":"Maurya",
"middleName":"",
"mobile":"9733892132",
"email":"monalisa@gmail.com",
"password":"monalisa"
}


-----------