import os
from flask import *
from werkzeug.utils import secure_filename

from src.database import*
app=Flask(__name__)
import functools
def login_required(func):
    @functools.wraps(func)
    def secure_function():
        if "LID" not in session:
            return redirect ("/")
        return func()
    return secure_function
app.secret_key="grtrhghf"

@app.route('/logout')
def logout():
    session.clear()
    return render_template("login.html")

@app.route('/')
def Start_page():
    return render_template('index1.html')

@app.route('/loginn')
def loginn():
    return render_template('login.html')
@app.route('/login',methods=['post'])
def login():
    username=request.form['textfield']
    password=request.form['textfield2']
    qry ="select * from login where username=%s and password=%s"
    val=(username,password)
    res=selectone(qry,val)
    print("res",res)
    if res is None:
        return'''<script>alert("invalid");window.location="/"</script>'''
    elif res[3]=='admin':
        session['LID']=res[0]
        return '''<script>alert("login succesfully");window.location="/adminhome"</script>'''
    elif res[3]=='medicalofficer':
        session['LID']=res[0]
        return '''<script>alert("login sucessfully");window.location="/medicalofficerhome"</script>'''
    elif res[3]=='legalassistant':
        session['LID']=res[0]
        return '''<script>alert("login sucessfully");window.location="/legalassisthome"</script>'''
    elif res[3]=='localcordinator':
         session['LID']=res[0]
         return '''<script>alert("login sucessfully");window.location="/localcordinatorhome"</script>'''
    else:
          return '''<script>alert("invalid");</script>'''



@app.route('/adminhome')
@login_required
def adminhome():
    return render_template('adminhome.html')



@app.route('/addeducation',methods=['post','get'])
@login_required

def addeducation():
    return render_template('addeducation.html')

@app.route('/toaddeducation',methods=['post','get'])
@login_required

def toaddeducation():
    name=request.form['textfield']
    place=request.form['textfield2']
    post=request.form['textfield3']
    pin=request.form['textfield4']
    phone=request.form['textfield5']
    email=request.form['textfield6']
    document=request.files['file']
    fname=secure_filename(document.filename)
    document.save(os.path.join('static/upload', fname))

    qry="insert into `educationsupport` values(NULL,%s,%s,%s,%s,%s,%s,%s)"
    val=(name,place,post,pin,phone,email,document)
    iud(qry,val)
    return '''<script>alert("added");window.location='/manageeducation#about'</script>'''


@app.route('/manageeducation')
@login_required

def manageeducation():
    qry="select * from `educationsupport`"
    res=selectall(qry)

    return render_template('manageeducation.html',val=res)



@app.route('/editmanageeducation')
@login_required

def editmanageeducation():
    id=request.args.get('id')
    session['eid']=id
    qry="SELECT * FROM `educationsupport` WHERE `educationinstitute_id`=%s"
    res=selectone(qry,id)
    return render_template('editmanageeducation.html',val=res)

@app.route('/toeditmanageeducation',methods=['post'])
@login_required

def toeditmanageeducation():
    educationalinstitutename=request.form['textfield']
    place=request.form['textfield']
    post=request.form['textfield3']
    pin=request.form['textfield4']
    phone=request.form['textfield5']
    email=request.form['textfield6']
    document=request.files['file']
    fname = secure_filename(document.filename)
    document.save(os.path.join('static/upload', fname))

    qry="UPDATE `educationsupport` SET `educationinstitutename`=%s,`place`=%s,`post`=%s,`pin`=%s,`contactno`=%s,`email`=%s,`document`=%s WHERE `educationinstitute_id`=%s"
    val=(educationalinstitutename,place,post,pin,phone,email,fname,session['eid'])
    iud(qry,val)
    return '''<script>alert("updated");window.location="/manageeducation"</script>'''



@app.route('/deletemanageeducation')
@login_required

def deletemanageeducation():
    id=request.args.get('id')
    qry="DELETE FROM `educationsupport` WHERE `educationinstitute_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/manageeducation"</script>'''




@app.route('/addjobs',methods=['post','get'])
@login_required

def addjobs():
    return render_template('addjobs.html')

@app.route('/toaddjob',methods=['post','get'])
@login_required

def toaddjob():
    jobtype=request.form['textfield']
    place=request.form['textfield2']
    post=request.form['textfield3']
    contact=request.form['textfield4']

    qry="insert into `jobsupport` values(NULL,%s,%s,%s,%s)"
    val=(jobtype,place,post,contact)
    iud(qry,val)
    return '''<script>alert("added");window.location='/managejobs#about'</script>'''


@app.route('/managejobs')
@login_required

def managejobs():
    qry="select * from `jobsupport`"
    res=selectall(qry)

    return render_template('managejobs.html',val=res)



@app.route('/editmanagejobs')
@login_required

def editmanagejobs():
    id=request.args.get('id')
    session['eid']=id
    qry="SELECT * FROM `jobsupport` WHERE `job_id`=%s"
    val=(id)
    res=selectone(qry,val)
    print(res)
    return render_template('editmanagejobs.html',val=res)



@app.route('/toeditmanagejob',methods=['post'])
@login_required

def toeditmanagejob():
    jobtype=request.form['textfield']
    place=request.form['textfield2']
    post=request.form['textfield3']
    contactno=request.form['textfield4']

    qry="UPDATE `jobsupport` SET `jobtype`=%s,`place`=%s,`post`=%s,`contactno`=%s WHERE `job_id`=%s"
    val=(jobtype,place,post,contactno,session['eid'])
    iud(qry,val)
    return '''<script>alert("updated");window.location="/managejobs#about"</script>'''




@app.route('/deletemanagejobs')
@login_required

def deletemanagejobs():
    id=request.args.get('id')
    qry="DELETE FROM `jobsupport` WHERE `job_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managejobs"</script>'''







@app.route('/addlegalassistant',methods=['post','get'])
@login_required
def addlegalassistant():
    return render_template('addlegalassistant.html')


@app.route('/toaddlegalassistant',methods=['post','get'])
@login_required

def toaddlegalassistant():
    name=request.form['textfield']
    gender=request.form['radiobutton']
    place=request.form['textfield2']
    post=request.form['textfield3']
    pin=request.form['textfield4']
    phone=request.form['textfield5']
    email=request.form['textfield6']
    document=request.files['file']
    fname=secure_filename(document.filename)
    document.save(os.path.join('static/upload',fname))

    username=request.form['textfield7']
    password=request.form['textfield8']
    q = selectone2("select * from login where USERNAME='" + username + "'")
    if q is None:
            qry="insert into login values(NULL,%s,%s,'legalassistant')"
            val=(username,password)
            id=iud(qry,val)
            qry="insert into legalassistant values(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            val=(str(id),name,gender,place,post,pin,phone,email,document)
            iud(qry,val)
            return'''<script>alert("added");window.location='/managelegalassistant#about'</script>'''
    else:
        return '''<script>alert("Already exists");window.location='/'</script>'''


@app.route('/managelegalassistant')
@login_required

def managelegalassistant():
    qry="select * from `legalassistant`"
    res=selectall(qry)
    return render_template('managelegalassistant.html',val=res)



@app.route('/editmanagelegalassistant')
@login_required

def editmanagelegalassistant():
    id=request.args.get('id')
    session['eid']=id
    qry="SELECT * FROM `legalassistant` WHERE `legalassistant_id`=%s"
    res=selectone(qry,id)
    return render_template('editmanagelegalassistant.html',val=res)


@app.route('/toeditmanagelegalassistant',methods=['post'])
@login_required

def toeditmanagelegalassistant():
    name=request.form['textfield']
    gender=request.form['radiobutton']
    place=request.form['textfield2']
    post=request.form['textfield3']
    pin=request.form['textfield4']
    phone=request.form['textfield5']
    email=request.form['textfield6']
    document=request.files['file']
    fname=secure_filename(document.filename)
    document.save(os.path.join('static/upload',fname))



    qry = "UPDATE `legalassistant` SET `name`=%s,`gender`=%s,`place`=%s,`post`=%s,`pin`=%s,`contactno`=%s,`email`=%s,`document`=%s WHERE `legalassistant_id`=%s"
    val = (name,gender,place,post,pin,phone,email,fname,session['eid'])
    iud(qry, val)
    return '''<script>alert("updated");window.location="/managelegalassistant#about"</script>'''



@app.route('/deletemanagelegalassistant')
@login_required

def deletemanagelegalassistant():
    id=request.args.get('id')
    qry="DELETE FROM `legalassistant` WHERE `legalassistant_id`=%s"
    iud(qry,id)
    qry="DELETE FROM `login` WHERE `LID`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managelegalassistant"</script>'''








@app.route('/addmedicalofficer',methods=['post'])

@login_required

def addmedicalofficer():
    return render_template('addmedicalofficer.html')



@app.route('/toaddmedicalofficer',methods=['post'])
@login_required

def toaddmedicalofficer():
    name=request.form['textfield']
    hosp=request.form['hospital']
    gender=request.form['radiobutton']
    place=request.form['textfield2']
    post=request.form['textfield3']
    pin=request.form['textfield4']
    phone=request.form['textfield5']
    email=request.form['textfield6']
    specialization = request.form['select']
    username=request.form['textfield7']
    password=request.form['textfield8']
    q = selectone2("select * from login where USERNAME='" + username + "'")
    if q is None:
        qry="insert into login values(NULL,%s,%s,'medicalofficer')"
        val=(username,password)
        id=iud(qry,val)
        qry="insert into medicalofficer values(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        val=(name,gender,place,post,pin,phone,email,str(id),specialization,hosp)
        iud(qry,val)
        return'''<script>alert("added");window.location='/managemedicalofficer#about'</script>'''
    else:
        return '''<script>alert("Already exists");window.location='/'</script>'''


@app.route('/managemedicalofficer')
@login_required

def managemedicalofficer():
    qry="select * from `medicalofficer`"
    res=selectall(qry)

    return render_template('managemedicalofficer.html',val=res)




@app.route('/editmanagemedicalofficer')
@login_required

def editmanagemedicalofficer():
    id=request.args.get('id')
    session['eid']=id
    qry="SELECT * FROM `medicalofficer` WHERE `medicalofficer_id`=%s"
    res=selectone(qry,id)
    return render_template('editmanagemedicalofficer.html',val=res)



@app.route('/toeditmanagemedicalofficer',methods=['post'])
@login_required

def toeditmanagemedicalofficer():
    name = request.form['textfield']
    hospital = request.form['hospital']
    gender = request.form['radiobutton']
    place = request.form['textfield2']
    post = request.form['textfield3']
    pin = request.form['textfield4']
    phone = request.form['textfield5']
    email = request.form['textfield6']
    specialization = request.form['select']


    qry = "UPDATE `medicalofficer` SET `name`=%s,`gender`=%s,`place`=%s,`post`=%s,`pin`=%s,`contact no`=%s,`email`=%s,`specialization`=%s,hospital=%s WHERE `medicalofficer_id`=%s"
    val = (name,gender,place,post,pin,phone,email,specialization,hospital,session['eid'])
    iud(qry, val)
    return '''<script>alert("updated");window.location="/managemedicalofficer#about"</script>'''


@app.route('/deletemanagemedicalofficer')
@login_required
def deletemanagemedicalofficer():
    id=request.args.get('id')
    qry="DELETE FROM `medicalofficer` WHERE `medicalofficer_id`=%s"
    iud(qry,id)
    qry="DELETE FROM `login` WHERE `LID`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managemedicalofficer"</script>'''






@app.route('/approvelocalcordinator')
@login_required

def approvelocalcordinator():
     qry="SELECT  localcordinator .*,`login`.`TYPE` FROM `localcordinator` JOIN `login` ON `localcordinator`.`LID`=`login`.`LID` WHERE `login`.`TYPE`='pending'"
     res=selectall(qry)
     return render_template('approve local cordinator.html',val=res)


@app.route('/acceptlocalcordinator')
@login_required

def acceptlocalcordinator():
    id=request.args.get('id')
    qry="update login set type='localcordinator' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return'''<script>alert("approved");window.location="/viewapprovelocalcordinator"</script>'''

@app.route('/rejectlocalcordinator')
@login_required

def rejectlocalcordinator():
    id=request.args.get('id')
    qry="update login set type='rejected' where LID=%s"
    val=(id)
    iud(qry,val)
    return'''<script>alert("reject");window.location="/approvelocalcordinator"</script>'''






@app.route('/viewapprovelocalcordinator')
@login_required

def viewapprovelocalcordinator():

    qry="SELECT  localcordinator .*,`login`.`TYPE` FROM `localcordinator` JOIN `login` ON `localcordinator`.`LID`=`login`.`LID` WHERE `login`.`TYPE`='localcordinator'"
    res=selectall(qry)

    return render_template('viewapprovelocalcordinator.html',val=res)










@app.route('/approvetransuser')
@login_required

def approvetransuser():
    qry="SELECT`transgender`.*,`login`.`TYPE`FROM`transgender`JOIN`login`ON`transgender`.`lid`=`login`.`LID` WHERE `login`.`TYPE`='pending'"
    res=selectall(qry)
    return render_template('approvetransuser.html',val=res)


@app.route('/accepttransuser')
@login_required

def accepttransuser():
    id=request.args.get('id')
    qry="update login set type='user' where LID=%s"
    val=(str(id))
    iud(qry,val)
    return'''<script>alert("approved");window.location="/viewapprovetransuser"</script>'''



@app.route('/rejecttransuser')
@login_required

def rejecttransuser():
    id=request.args.get('id')
    qry="update login set type='rejected' where LID=%s"
    val=(id)
    iud(qry,val)
    return'''<script>alert("reject");window.location="/approvetransuser"</script>'''




@app.route('/viewapprovetransuser')
@login_required

def viewapprovetransuser():
    qry = "SELECT`registration`.*,`login`.`TYPE`FROM`registration`JOIN`login`ON`registration`.`LID`=`login`.`LID` WHERE `login`.`TYPE`='transuser'"
    res=selectall(qry)
    return render_template('viewapprovetransuser.html',val=res)







@app.route('/changepassword')
@login_required

def changepassword():
    return render_template('changepassword.html')




@app.route('/chat')
@login_required

def chat():
    qry="SELECT `transgender`.* FROM `transgender` JOIN `login` ON `transgender`.`lid`=`login`.`LID` WHERE `login`.`TYPE`='user'"
    res=selectall(qry)
    return render_template('chat.html',val=res)



@app.route("/chatsp1")
@login_required

def chatsp1():
    sid=request.args.get('id')
    session['sid']=sid
    qry="SELECT `transgender`.* FROM `transgender` WHERE LID=%s"
    res=selectone(qry,sid)
    qry="SELECT * FROM `chat` WHERE `from_id`=%s AND `to_id`=%s or `from_id`=%s AND `to_id`=%s "
    val=(session['LID'],session['sid'],session['sid'],session['LID'])
    res1=selectall2(qry,val)
    fname=res[2]

    return render_template("chatsp.html",data=res1,fname=fname,fr=session['sid'])







@app.route('/sendmsg2',methods=['post'])
@login_required

def sendmsg2():

    msg=request.form['textarea']
    print(msg)
    qry="INSERT INTO `chat` VALUES(NULL,%s,%s,%s,now())"
    val=(session['LID'],session['sid'],msg)
    iud(qry,val)
    qry = "SELECT `transgender`.* FROM `transgender` WHERE lid=%s"
    res = selectone(qry, session['sid'])
    qry = "SELECT * FROM `chat` WHERE `from_id`=%s AND `to_id`=%s or `from_id`=%s AND `to_id`=%s"
    val = (session['LID'], session['sid'], session['sid'], session['LID'])
    res1 = selectall2(qry, val)
    fname = res[2]
    print(fname)


    return render_template('chatsp.html',data=res1,fname=fname,fr=session['sid'])





@app.route('/addhumanrights',methods=['post'])
@login_required

def addhumanrights():
    return render_template('addhumanrights.html')


@app.route('/toaddhumanrights',methods=['post'])
@login_required

def toaddhumanrights():
    typeofhumanrights=request.form['select']
    description=request.form['textarea']

    qry="insert into humanrights values(NULL,%s,%s,%s)"
    val=(session['LID'],typeofhumanrights,description)
    iud(qry,val)
    return '''<script>alert("added");window.location='/managehumanrights#about'</script>'''



@app.route('/managehumanrights')
@login_required

def managehumanrights():
    qry="select * from `humanrights` where l_id='"+str(session['LID'])+"'"
    res=selectall(qry)
    return render_template('managehumanrights.html',val=res)


@app.route('/deletemanagehumanrights')
@login_required

def deletemanagehumanrights():
    id=request.args.get('id')
    qry="DELETE FROM `humanrights` WHERE `hr_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managehumanrights"</script>'''






@app.route('/addmeetingnotification',methods=['post'])
@login_required

def addmeetingnotification():
    return render_template('addmeetingnotification.html')

@app.route('/toaddmeetingnotification',methods=['post'])
@login_required

def toaddmeetingnotification():
    meetingtime=request.form['textfield']
    place=request.form['textfield2']
    description=request.form['textarea']

    qry="insert into notification values(NULL,%s,%s,%s,%s)"
    val=(session['LID'],meetingtime,place,description)
    iud(qry, val)
    print(meetingtime)
    return '''<script>alert("added");window.location='/managemeetingnoti#about'</script>'''


@app.route('/managemeetingnoti')
@login_required

def managemeetingnoti():
    qry = "select * from `notification` where legal_id='"+str(session['LID'])+"'"
    res=selectall(qry)
    return render_template('managemeetingnoti.html',val=res)

@app.route('/deletemanagemeetingnoti')
@login_required

def deletemanagemeetingnoti():
    id=request.args.get('id')
    qry="DELETE FROM `notification` WHERE `meeting_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managemeetingnoti"</script>'''




@app.route('/addschedule',methods=['post'])
@login_required

def addschedule():
    return render_template('addschedule.html')

@app.route('/toaddschedule',methods=['post'])
def toaddschedule():
    date=request.form['textfield']
    fromtime=request.form['textfield2']
    totime=request.form['textfield3']

    qry="insert into schedule values(NULL,%s,%s,%s,%s)"
    val=((session['LID']),date,fromtime,totime)
    iud(qry, val)
    return '''<script>alert("added");window.location='/manageschedule#about'</script>'''


@app.route('/manageschedule')
@login_required

def manageschedule():
    qry = "select * from `schedule` where legal_id='"+str(session['LID'])+"'"
    res=selectall(qry)
    return render_template('manageschedule.html',val=res)

@app.route('/deletemanageschedule')
@login_required

def deletemanageschedule():
    id=request.args.get('id')
    qry="DELETE FROM `schedule` WHERE `schedule_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/manageschedule#about"</script>'''



@app.route('/acceptbooking')
@login_required

def acceptbooking():
    qry="SELECT `schedule`.*,`transgender`.`name`, booking.book_id FROM `booking` JOIN`transgender`ON `transgender`.`lid`=`booking`.`from_id`JOIN`schedule`ON`booking`.`schedule_id`=`schedule`.`schedule_id`WHERE `status`='pending' and schedule.legal_id='"+str(session['LID'])+"'"
    res=selectall(qry)
    print(res)
    return render_template('acceptbooking.html',val=res)


@app.route('/toacceptbooking')
@login_required

def toacceptbooking():
    id=request.args.get('id')
    qry="update booking set status='booked' where `book_id`=%s"
    val=(str(id))
    iud(qry,val)
    return'''<script>alert("approved");window.location="/viewacceptbooking"</script>'''



@app.route('/rejectbooking')
@login_required

def rejectbooking():
    id=request.args.get('id')
    qry="update booking set status='rejected' where `book_id`=%s"
    val=(id)
    iud(qry,val)
    return'''<script>alert("reject");window.location="/acceptbooking"</script>'''




@app.route('/viewacceptbooking')
@login_required

def viewacceptbooking():
    qry = "SELECT `schedule`.*,`transgender`.`name`,booking.book_id FROM`booking` JOIN`transgender`ON `transgender`.`lid`=`booking`.`from_id`JOIN`schedule`ON`booking`.`schedule_id`=`schedule`.`schedule_id`WHERE `status`='booked'"
    res=selectall(qry)
    return render_template('viewacceptbooking.html',val=res)


@app.route('/legalassisthome')
@login_required
def legalassisthome():
    return render_template('legalassisthome.html')

@app.route('/addawernessprgm',methods=['post'])
@login_required
def addawernessprgm():
    res = selectone2("select date_add(curdate(),interval 1 day) as d")
    return render_template('addawernessprgm.html',t=res[0])

@app.route('/toaddawernessprgm',methods=['post'])
@login_required
def toaddawernessprgm():
    programname=request.form['textfield']
    description=request.form['textfield2']
    date=request.form['textfield5']
    time=request.form['textfield3']
    venue=request.form['textfield4']
    qry = "insert into awarness values(NULL,%s,%s,%s,%s,%s,%s)"
    val = ((session['LID']),programname,description,date,time,venue)
    iud(qry, val)
    return '''<script>alert("added");window.location='/manageawerness#about'</script>'''


@app.route('/manageawerness')
@login_required
def manageawerness():
    qry = "select * from `awarness` where awarness.location_id='"+str(session['LID'])+"'"
    res = selectall(qry)
    return render_template('manageawerness.html',val=res)


@app.route('/deletemanageawerness')
@login_required

def deletemanageawerness():
    id=request.args.get('id')
    qry="DELETE FROM `awarness` WHERE `awerness_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/manageawerness"</script>'''



@app.route('/localcordinatorhome')
@login_required

def localcordinatorhome():
    return render_template('localcordinatorhome.html')

@app.route('/userreg')
def userreg():
    return render_template('userreg.html')
@app.route('/userregister',methods=['post'])
def userregister():

        name=request.form['textfield']
        gender=request.form['gender']
        house=request.form['house']



        place=request.form['textfield2']
        post=request.form['textfield3']
        pin= request.form['textfield4']
        phone=request.form['textfield5']
        assignedarea=request.form['textfield10']
        email = request.form['textfield6']
        qualification = request.form.getlist('checkbox')
        q = str(','.join(qualification))
        username=request.form['textfield7']
        password=request.form['textfield8']

        # photo=request.files['file']
        # fname=secure_filename(photo.filename)
        # photo.save(os.path.join('static/upload',fname))
        qq = selectone2("select * from login where USERNAME='" + username + "'")
        if qq is None:

            qry1="insert into login values(NUll,%s,%s,'pending')"
            val1=(username,password)
            id=iud(qry1,val1)
            qry="insert into localcordinator values(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            val=(str(id),name,place,post,pin,phone,assignedarea,email,q,gender,house)
            iud(qry,val)
            return'''<script>alert("registerd");window.location='/'</script>'''

        else:
            return'''<script>alert("Already exists");window.location='/'</script>'''




@app.route('/addevent',methods=['post'])
@login_required

def addevent():
    res=selectone2("select date_add(curdate(),interval 1 day) as d")
    return render_template('addevent.html',t=res[0])


@app.route('/toaddevent',methods=['post'])

@login_required

def toaddevent():
    eventname=request.form['textfield']
    description=request.form['textarea']
    venue=request.form['textfield2']
    date=request.form['textfield3']
    time=request.form['textfield4']

    qry = "insert into events values(NULL,%s,%s,%s,%s,%s,%s)"
    val = ((session['LID']),eventname,description,venue,date,time)
    iud(qry, val)
    return '''<script>alert("added");window.location='/manageevent'</script>'''






@app.route('/manageevent')
@login_required

def manageevent():
    qry = "select * from `events` where location_id='"+str(session['LID'])+"'"
    res = selectall(qry)
    return render_template('manageevent.html',val=res)


@app.route('/deletemanageevent')
@login_required

def deletemanageevent():
    id=request.args.get('id')
    qry="DELETE FROM `events` WHERE `event_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/manageevent"</script>'''






@app.route('/addcouncillingprgm',methods=['post'])
@login_required

def addcouncillingprgm():
    res=selectone2("select date_add(curdate(),interval 1 day)  as d")
    return render_template('addcouncillingprgm.html',t=res[0])



@app.route('/toaddcouncillingprgm',methods=['post'])
@login_required

def toaddcouncillingprgm():
    counsellingprogram=request.form['textfield']
    description=request.form['textfield2']
    place=request.form['textfield3']
    date=request.form['textfield4']
    time=request.form['textfield5']
    contactno=request.form['textfield6']

    qry="insert into counselling values(NULL,%s,%s,%s,%s,%s,%s,%s)"
    val=(session['LID'],counsellingprogram,description,place,date,time,contactno)
    iud(qry,val)
    return'''<script>alert("added");window.location='/managecouncillingprgm#about'</script>'''


@app.route('/managecouncillingprgm')
@login_required

def managecouncillingprgm():
    qry="select * from `counselling` where cid='"+str(session['LID'])+"' "
    res=selectall(qry)
    return render_template('managecouncellingprgm.html',val=res)



@app.route('/deletemanagecouncillingprgm')
@login_required

def deletemanagecouncillingprgm():
    id=request.args.get('id')
    qry="DELETE FROM `counselling` WHERE `medicalofficer_id`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managecouncillingprgm"</script>'''





@app.route('/addhealthtip',methods=['post'])
@login_required

def addhealthtip():
    return render_template('addhealthtip.html')


@app.route('/toaddhealthtip',methods=['post'])
@login_required

def toaddhealthtip():
    jobtype=request.form['text']
    description=request.form['textfield2']


    qry="insert into healthtip values(NULL,%s,%s,%s)"
    val=(session['LID'],jobtype,description)
    iud(qry,val)
    return'''<script>alert("added");window.location='/managehealthtip#about'</script>'''



@app.route('/managehealthtip')
@login_required

def managehealthtip():
    qry = "select * from `healthtip` where lid='"+str(session['LID'])+"'"
    res = selectall(qry)
    return render_template('managehealthtip.html',val=res)

@app.route('/deletemanagehealthtip')
@login_required

def deletemanagehealthtip():
    id=request.args.get('id')
    qry="DELETE FROM `healthtip` WHERE `hid`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/managehealthtip#about"</script>'''



@app.route('/medicalofficerhome')
@login_required

def medicalofficerhome():
    return render_template('medicalofficerhome.html')


@app.route('/help')
@login_required

def help():
    qry="SELECT `transgender`.`name`,`emergency_help`.* FROM `emergency_help` JOIN `transgender` ON `transgender`.`lid`=`emergency_help`.`transgender_id`"
    res=selectall(qry)
    return render_template('help.html',val=res)



@app.route("/report",methods=['post','get'])
@login_required
def report():

    return render_template("viewreport.html")



@app.route("/report1",methods=['post'])
@login_required
def report1():
    m=request.form['m']
    q="SELECT `booking`.* ,`legalassistant`.*,COUNT(*) FROM `booking` LEFT JOIN `schedule` ON `schedule`.`schedule_id`=`booking`.`schedule_id` JOIN `legalassistant` ON `legalassistant`.LOGIN_ID=`schedule`.legal_id WHERE MONTH(`booking`.`booking_date`)=%s AND YEAR(`booking`.`booking_date`)=YEAR(CURDATE()) GROUP BY `legalassistant`.`LOGIN_ID` "
    res=selectall2(q,m)
    return render_template("viewreport.html",val=res,mo=m)

@app.route("/addreport",methods=['post','get'])
@login_required
def addreport():
    return render_template("addreport.html")

@app.route("/upload",methods=['post','get'])
@login_required
def upload():
    month=request.form['textfield']
    document = request.files['file']
    fname = secure_filename(document.filename)
    document.save(os.path.join('static/report', fname))
    q="insert into report values(null,%s,curdate(),%s,%s)"
    v=session['LID'],month,fname
    iud(q,v)
    return '''<script>alert("Added");window.location='/report#about'</script>'''

@app.route('/delreport')
@login_required

def delreport():
    id=request.args.get('id')
    qry="DELETE FROM `report` WHERE `rid`=%s"
    iud(qry,id)
    return '''<script>alert("deleted");window.location="/report#about"</script>'''



@app.route("/c1")
@login_required
def c1():

    return render_template("achange password.html")


@app.route('/passwordchange',methods=['get','post'])
def passwordchange():


            currentpassword=request.form['textfield']

            newpassword = request.form['textfield2']

            confirmpassword = request.form['textfield3']

            q=("SELECT * FROM `login` WHERE `login`.`LID`=%s and password=%s")
            v=session['LID'],currentpassword
            s=selectone(q,v)

            if s is None:
               return  '''<script>alert("incorrect password");window.location='/c1#about'</script>'''
            elif newpassword==confirmpassword:
               q=("update login set password=%s where LID=%s")
               v=newpassword,session['LID']
               iud(q,v)
               return '''<script>alert("password changed");window.location='/'</script>'''
            else:
               return '''<script>alert("password missmatch");window.location='/c1#about'</script>'''









@app.route("/c2")
@login_required
def c2():

    return render_template("aachange password.html")


@app.route('/passwordchange2',methods=['get','post'])
def passwordchange2():


            currentpassword=request.form['textfield']

            newpassword = request.form['textfield2']

            confirmpassword = request.form['textfield3']

            q=("SELECT * FROM `login` WHERE `login`.`LID`=%s and password=%s")
            v=session['LID'],currentpassword
            s=selectone(q,v)

            if s is None:
               return  '''<script>alert("incorrect password");window.location='/c2#about'</script>'''
            elif newpassword==confirmpassword:
               q=("update login set password=%s where LID=%s")
               v=newpassword,session['LID']
               iud(q,v)
               return '''<script>alert("password changed");window.location='/'</script>'''
            else:
               return '''<script>alert("password missmatch");window.location='/c2#about'</script>'''


@app.route("/c3")
@login_required
def c3():

    return render_template("aaachange password.html")


@app.route('/passwordchange3',methods=['get','post'])
def passwordchange3():


            currentpassword=request.form['textfield']

            newpassword = request.form['textfield2']

            confirmpassword = request.form['textfield3']

            q=("SELECT * FROM `login` WHERE `login`.`LID`=%s and password=%s")
            v=session['LID'],currentpassword
            s=selectone(q,v)

            if s is None:
               return  '''<script>alert("incorrect password");window.location='/c3#about'</script>'''
            elif newpassword==confirmpassword:
               q=("update login set password=%s where LID=%s")
               v=newpassword,session['LID']
               iud(q,v)
               return '''<script>alert("password changed");window.location='/'</script>'''
            else:
               return '''<script>alert("password missmatch");window.location='/c3#about'</script>'''

@app.route("/c4")
@login_required
def c4():

    return render_template("aaaachange password.html")


@app.route('/passwordchange4',methods=['get','post'])
def passwordchange4():


            currentpassword=request.form['textfield']

            newpassword = request.form['textfield2']

            confirmpassword = request.form['textfield3']

            q=("SELECT * FROM `login` WHERE `login`.`LID`=%s and password=%s")
            v=session['LID'],currentpassword
            s=selectone(q,v)

            if s is None:
               return  '''<script>alert("incorrect password");window.location='/c4#about'</script>'''
            elif newpassword==confirmpassword:
               q=("update login set password=%s where LID=%s")
               v=newpassword,session['LID']
               iud(q,v)
               return '''<script>alert("password changed");window.location='/'</script>'''
            else:
               return '''<script>alert("password missmatch");window.location='/c4#about'</script>'''



app.run(debug=True)



