        <!--== Page Title Area Start ==-->
        <section id="page-title-area" class="section-padding overlay">
            <div class="container">
                <div class="row">
                    <!-- Page Title Start -->
                    <div class="col-lg-12">
                        <div class="section-title  text-center">
                            <h2><%=session.getAttribute("t1")%></h2>
                            <span class="title-line"><i class="fa fa-heartbeat"></i></span>
                            <p><%=session.getAttribute("t2")%></p>
                        </div>
                    </div>
                    <!-- Page Title End -->
                </div>
            </div>
        </section>
        <!--== Page Title Area End ==-->

        <!--== Login Page Content Start ==-->
        <p class="message-attribut">
            <%  
                String attribut=(String)request.getAttribute("message");
                boolean b = attribut.toLowerCase().contains("erreur");
                if (b==true){%>
                    <span class="message_erreur">
                        <%out.println(attribut);%>
                    </span>
                <% } else {%>
                    <span class="message_normal">
                        <%out.println(attribut);%>
                    </span>
                <% }
            %>
        </p>