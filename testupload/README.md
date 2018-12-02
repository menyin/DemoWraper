##此demo用于解决在IE8下，非flash文件上传时，出现跨域问题。
##具体问题：
### * 在IE8下不用flash，用iframe模拟无刷新上传文件时，客户端请求服务端同域名可以正确返回结果信息；
### * 在分布式环境下，各个项目要公用一个上传js插件，上传插件的服务端和客户端必须单独一套；
### * 所以别的业务项目引用前端插件去访问上传插件的服务端，就出现了IE跨域不能正确返回结果信息问题；
##解决方案：
### * 利用nginx反向代理，使得每个业务项目都有一个/upload路径代理到上传插件的服务端；例如：www.597.com/upload；   
### * 在使用上传js插件里的上传url直接写www.597.com/upload，或者所有业务项目用到时都写/upload即可；
### * 相当于每个业务项目都有一个/upload的服务端接口。 
### * 注意：上传插件服务端：支持跨域（如用@CrossOrigin）、响应头类型为content-type: text/html; charset=utf-8

##实践中出现问题：
### * nginx配置server_name为www.upload.ccss，并且location为/upload 此时http://m.upload.ccss/upload访问，实际是访问http:192.168.1.246/upload 
###   nginx配置server_name为*.upload.ccss，并且location为/upload 此时http://m.upload.ccss/upload访问，实际是访问http:192.168.1.246/  
### * 如何才能不影响动到原有业务项目配置（如www.597.com的配置），另外单独配置一个*.597.com/upload来使得所有业务项目都能有一个上传接口
###   否则就要增加一个业务项目就要在其server_name下配置一个值为/upload的location， 事实是也不会麻烦，就怕忘记。

##部署过程：
### *csupload目录是前端测试项目，部署在一个tomcat,端口8081
### *src目录是maven上传插件后端服务，部署在一个tomcat，端口8080
### *在hosts文件里为以上两个ip配置域名
### *启动一个nginx，在每个业务项目的server_name里最前面配置一个location用于映射到上传插件后端服务，如：
 server {
       	listen       80;
      	server_name  m.597.ccss;
        	location /upload {
            	 proxy_pass   http://uploadServer/;
                           port_in_redirect   on;
                           proxy_redirect     off;
                           proxy_set_header   Host             $host;
                           proxy_set_header   X-Real-IP        $remote_addr;
                           proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
       		 }

       		location / {
                           	 proxy_pass   http://mServer;
                      		 }
        }
### *启动前后端项目和nginx进行测试。
