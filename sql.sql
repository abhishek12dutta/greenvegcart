use green_veg_cart_db_instance;

commit;

SELECT * FROM oauth_access_token;

SELECT * FROM oauth_refresh_token;

delete from oauth_access_token;

delete from oauth_refresh_token;


rollback;

commit;


select token_id, token from oauth_refresh_token where token_id = '01114209dd774bafb5b9778279ac4a2e'