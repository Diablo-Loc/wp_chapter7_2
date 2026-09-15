FROM tomcat:10.1

# Xóa các ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Đổi cổng shutdown mặc định thành -1 để vô hiệu hóa lệnh shutdown qua socket
RUN sed -i 's/port="8005" shutdown="SHUTDOWN"/port="-1" shutdown="SHUTDOWN"/' /usr/local/tomcat/conf/server.xml

# Copy file WAR vào Tomcat
COPY BT4-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# Chạy Tomcat ở chế độ foreground
CMD ["catalina.sh", "run"]
