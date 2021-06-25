

//定义文件保存位置
char *filename = "E:\\data\\failRegist.log";
long file_stream;

Action()
{


web_add_cookie("PHPSESSID=cv4gtb5hglsukgcacacuid5hc8; DOMAIN=js.chinavolunteer.cn");

web_add_cookie("Hm_lvt_96c55a3847063dc0f3d6d32b55654297=1544159561; DOMAIN=js.chinavolunteer.cn");

web_add_cookie("Hm_lvt_96c55a3847063dc0f3d6d32b55654297=1544159561,1544161429; DOMAIN=js.chinavolunteer.cn");

web_add_cookie("Hm_lpvt_96c55a3847063dc0f3d6d32b55654297=1544161429; DOMAIN=js.chinavolunteer.cn");

web_url("register.php",
"URL=http://js.chinavolunteer.cn/app/user/register.php",
"Resource=0",
"RecContentType=text/html",
"Referer=",
"Snapshot=t1.inf",
"Mode=HTML",
EXTRARES,
"Url=http://www.bing.com/favicon.ico", "Referer=", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/btn.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/green/images/topbg.jpg", ENDITEM,
"Url=http://css.zhiyuanyun.com/green/images/tsearch.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/qgzy2.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/syslog.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/green/images/navbg.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/buts.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/tab1.png", ENDITEM,
"Url=/css/lib/images/tips.png", ENDITEM,
LAST);


lr_error_message("userName== %s", lr_eval_string("{userName}"));
lr_error_message("cordNo== %s", lr_eval_string("{cordNo}"));
lr_error_message("userNick== %s", lr_eval_string("{userNick}"));
lr_error_message("sex== %s", lr_eval_string("{sex}"));
lr_error_message("userFace== %s", lr_eval_string("{userFace}"));
lr_error_message("userEdu== %s", lr_eval_string("{userEdu}"));
lr_error_message("birth_year== %s", lr_eval_string("{birth_year}"));
lr_error_message("birth_month== %s", lr_eval_string("{birth_month}"));
lr_error_message("birth_day== %s", lr_eval_string("{birth_day}"));





web_reg_find("SaveCount=userRepeat",
"Text=用户名已被他人使用",
LAST);

web_submit_data("register.php_2",
"Action=http://js.chinavolunteer.cn/app/user/register.php?m=uname_repeat",
"Method=POST",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t2.inf",
"Mode=HTML",
ITEMDATA,
"Name=login_name", "Value={userName}", ENDITEM,
LAST);

if ((file_stream = fopen(filename,"a+")) == NULL)
{
lr_error_message("Cannot open %s", filename);

}
fprintf(file_stream," %s ",lr_eval_string("{userName}"));

//保存需要的参数
if(atoi(lr_eval_string("{userRepeat}"))==1){
fprintf(file_stream," %s",",用户名已注册");
}



web_reg_find("SaveCount=cordRepeat","Text=该证件号码已被其他志愿者注册",LAST);

lr_think_time(80);

web_submit_data("register.php_3",
"Action=http://js.chinavolunteer.cn/app/user/register.php?m=cert_repeat",
"Method=POST",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t3.inf",
"Mode=HTML",
ITEMDATA,
"Name=vol_cert_number", "Value={cordNo}", ENDITEM,
"Name=vol_true_name", "Value={userNick}", ENDITEM,
LAST);


//保存需要的参数
if(atoi(lr_eval_string("{cordRepeat}"))==1){
fprintf(file_stream," %s",",证件号已被注册");
}

lr_think_time(80);

web_custom_request("view.php",
"URL=http://js.chinavolunteer.cn/app/api/view.php?m=get_tree_select&type=house_district&parent_id=6509&level=2",
"Method=POST",
"Resource=0",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t4.inf",
"Mode=HTML",
"EncType=",
LAST);

web_custom_request("view.php_2",
"URL=http://js.chinavolunteer.cn/app/api/view.php?m=get_tree_select&type=house_district&parent_id=6515&level=3",
"Method=POST",
"Resource=0",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t5.inf",
"Mode=HTML",
"EncType=",
LAST);

lr_think_time(80);

web_custom_request("view.php_3",
"URL=http://js.chinavolunteer.cn/app/api/view.php?m=get_tree_select&type=district&parent_id=6509&level=2",
"Method=POST",
"Resource=0",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t6.inf",
"Mode=HTML",
"EncType=",
LAST);

web_custom_request("view.php_4",
"URL=http://js.chinavolunteer.cn/app/api/view.php?m=get_tree_select&type=district&parent_id=6515&level=3",
"Method=POST",
"Resource=0",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t7.inf",
"Mode=HTML",
"EncType=",
LAST);

lr_think_time(80);

web_submit_data("register.php_4",
"Action=http://js.chinavolunteer.cn/app/user/register.php?m=uname_repeat",
"Method=POST",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t8.inf",
"Mode=HTML",
ITEMDATA,
"Name=login_name", "Value={userName}", ENDITEM,
LAST);
lr_think_time(80);

web_submit_data("register.php_5",
"Action=http://js.chinavolunteer.cn/app/user/register.php?m=cert_repeat",
"Method=POST",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t9.inf",
"Mode=HTML",
ITEMDATA,
"Name=vol_cert_number", "Value={cordNo}", ENDITEM,
"Name=vol_true_name", "Value={userNick}", ENDITEM,
LAST);

web_reg_find("SaveCount=isSuccess",
"Text=注册成功",
LAST);

web_submit_data("register.php_6",
"Action=http://js.chinavolunteer.cn/app/user/register.php?m=reg_vol",
"Method=POST",
"RecContentType=text/html",
"Referer=http://js.chinavolunteer.cn/app/user/register.php",
"Snapshot=t10.inf",
"Mode=HTML",
ITEMDATA,
"Name=token", "Value=23f3f31cc2e51edf3921c9869cce0b2d", ENDITEM,
"Name=login_name", "Value={userName}", ENDITEM,
"Name=login_name_repeat", "Value={userName}", ENDITEM,
"Name=login_pass", "Value=wl123456", ENDITEM,
"Name=login_pass_repeat", "Value=wl123456", ENDITEM,
"Name=login_email", "Value=2048151736%40qq.com", ENDITEM,
"Name=login_email_repeat", "Value=2048151736%40qq.com", ENDITEM,
"Name=vol_true_name", "Value={userNick}", ENDITEM,
"Name=vol_cert_number", "Value={cordNo}", ENDITEM,
"Name=gender", "Value={sex}", ENDITEM,
"Name=login_mobile", "Value={phone}", ENDITEM,
"Name=vol_phone", "Value=", ENDITEM,
"Name=login_qq", "Value=", ENDITEM,
"Name=vol_weibo", "Value=", ENDITEM,
"Name=house_district_txt", "Value=", ENDITEM,
"Name=vol_address", "Value=%E8%A5%BF%E5%B2%97%E8%A1%97%E9%81%93%E9%97%BB%E5%85%B0%E7%A4%BE%E5%8C%BA", ENDITEM,
"Name=postcode", "Value=", ENDITEM,
"Name=vol_student_flag", "Value=0", ENDITEM,
"Name=college_input", "Value=", ENDITEM,
"Name=college1", "Value=", ENDITEM,
"Name=college2", "Value=", ENDITEM,
"Name=college3", "Value=", ENDITEM,
"Name=vol_student_number", "Value=", ENDITEM,
"Name=vol_public_flag", "Value=1", ENDITEM,
"Name=district_txt", "Value=", ENDITEM,
"Name=stag_vol", "Value=%E7%A4%BE%E5%8C%BA%E6%9C%8D%E5%8A%A1", ENDITEM,
"Name=stag_vol_val", "Value=", ENDITEM,
"Name=stype_vol", "Value=23392%2C23394", ENDITEM,
"Name=vol_nationality", "Value=4544", ENDITEM,
"Name=vol_cert_type", "Value=4529", ENDITEM,
"Name=vol_reg_year", "Value={birth_year}", ENDITEM,
"Name=vol_reg_month", "Value={birth_month}", ENDITEM,
"Name=vol_reg_day", "Value={birth_day}", ENDITEM,
"Name=vol_political", "Value={userFace}", ENDITEM,
"Name=vol_ethnicity", "Value=4788", ENDITEM,
"Name=house_district1", "Value=6509", ENDITEM,
"Name=house_district2", "Value=6515", ENDITEM,
"Name=house_district3", "Value=", ENDITEM,
"Name=house_district4", "Value=", ENDITEM,
"Name=vol_edu_degree", "Value={userEdu}", ENDITEM,
"Name=vol_job_title", "Value=23276", ENDITEM,
"Name=graduate_year", "Value=", ENDITEM,
"Name=district1", "Value=6509", ENDITEM,
"Name=district2", "Value=6515", ENDITEM,
"Name=district3", "Value=", ENDITEM,
"Name=district4", "Value=", ENDITEM,
EXTRARES,
"Url=http://css.zhiyuanyun.com/default/images/wx_zyzg.png", ENDITEM,
"Url=http://css.zhiyuanyun.com/default/images/success.jpg", ENDITEM,
LAST);


if(atoi(lr_eval_string("{isSuccess}"))==1){
lr_error_message("isSuccess== %s", "注册成功");
//保存需要的参数
fprintf(file_stream," %s \n","--注册成功");

}else{

lr_error_message("isSuccess== %s", "注册失败");

//保存需要的参数

fprintf(file_stream," %s \n","--注册失败");


}
fclose(file_stream);
lr_think_time(120);
return 0;
}

