INSERT INTO USER(username, password) VALUES('ADMIN', 'ADMIN')
INSERT INTO WIDGETS(label, nbrinput) VALUES('meteo', 1)
INSERT INTO WIDGETS(label, nbrinput) VALUES('reddit', 2)
INSERT INTO WIDGETS(label, nbrinput) VALUES('youtube search', 1)
INSERT INTO SERVICES(label) VALUES('facebook')
INSERT INTO SERVICES(label) VALUES('youtube')
INSERT INTO LINKWIDGETSUSERTABLE(widgetsid, userid, linkstate, input, serviceId) VALUES(1, 1, 1, '0', 0)
INSERT INTO LINKWIDGETSUSERTABLE(widgetsid, userid, linkstate, input, serviceId) VALUES(2, 1, 1, '0', 0)
