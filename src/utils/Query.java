package utils;

public class Query {

	public static final String DepromoteAsMember = "";
	
	public static final String GetAllRequestsOfClan = "select * from ClanRequest where clanID like ?;";
	
	public static final String validateSession = "select sessionID from Session where mailID like ?";
	
	public static final String getCountOfAuthoredQuestions = "select count(*) as AuthoredQuestions from Questions where Author like ?";
	
	public static final String getCountOfCompletedSolutions = "select count(*) as CompletedSolutions from Solutions where status like 'COMPLETED' and mailID like ?;";
		
	public static final String getFullUserData = "select userName,score,streak,Datejoined,Streakdate,Ace_badge,Conquer_badge,Crown_badge,mailID from Users where mailID like ?";
	
	public static final String getHashedPasswd = ";";
	
	public static final String findRoleOfUser = ";";

	public static final String setPasswordToUser = ";";

	public static final String deleteClanByAdmin = "delete from Clan where clanID like ?;";

	public static final String getClanIDFromMailID = "select clanID from ClanRelation where mailID like ?;";

	public static final String checkAdminOfClanByAdminID = "select * from Clan where clanID like ? and Admin like ?;";

	public static final String checkMemberInAnyClan = "select * from ClanRelation where mailID like ?;";

	public static final String insertClanRequest = "insert into ClanRequest(clanID, mailID) values(?, ?);";

	public static final String AvailInThisClan = "select clanID from ClanRelation where mailID like ? and clanID like ?;";

	// public static final String GetAllAttemptedSolutions = "select distinct Q.Q_name, S.Sol, S.status, Q.levelID, L.level_name,Q.Q_ID from Solutions S join Questions Q on S.Q_ID = Q.Q_ID join Levels L on Q.levelID = L.levelID where status like 'ATTEMPTED' and S.mailID like ?;";

	public static final String GetClanDetails = "select U.userName, U.mailID, U.score, mail.role from Users U join(select mailID, role from ClanRelation where clanID like (select C.clanID from ClanRelation CR join Clan C on CR.ClanID = C.ClanID where mailID like ?)) as mail on U.mailID = mail.mailID order by U.score desc;";

	public static String deleteClanRequestsByMailID = "delete from ClanRequest where clanID like ? and mailID like ?;";

	public static String checkCoAdminOfaClan = "select role from ClanRelation where mailID like ? and clanID like ? and role like 'CO_ADMIN';";

	public static String getPasswordByMailID = "SELECT passwd FROM Login WHERE mailID like ?;";

	public static String CreateNewSession = "INSERT INTO Session (sessionID, mailID, loggedTime) VALUES (?, ?, ?);";

	public static String DeleteSession = "DELETE FROM Session where sessionID like ?";

	public static String getClanRole = "select role from ClanRelation where mailID like ? and clanID like ?;";

	public static String CreateAccount = "INSERT INTO Users (mailID, userName, Datejoined, Streakdate) VALUES (?, ?, ?, ?);";

	public static String InsertCredentials = "INSERT INTO Login (mailID, passwd) VALUES (?, ?);";

	public static String getLangListFromQuestionID = "select L.lang_name from LanguageRelation R join Languages L on L.l_ID = R.l_ID where Q_ID = ?;";

	public static String getAuthoredQuestionsOfaUser = "select q.Q_ID, q.Q_name, l.level_name from Questions q join Levels l on q.levelID = l.levelID where Author like ?;";

	public static String getAllCompletedSolutionsOfaUser = "select distinct Q.Q_ID, Q.Q_name, S.Sol, S.status, Q.levelID, L.level_name,Q.Q_ID, S.solDate, l.lang_name from Solutions S join Questions Q on S.Q_ID = Q.Q_ID join Levels L on Q.levelID = L.levelID join Languages l on l.l_ID = S.lang_ID where S.status like ? and S.mailID like ?";

	public static String getCountOfSolutionsForEachLang = "select count(Sol) as solCount from (select s.mailID, s.sol, l.lang_name from Solutions s join Languages l on s.lang_ID = l.l_ID) solutions where mailID like ? and solutions.lang_name like ?;";

	public static String getClanNameByClanID = "select clanName from Clan where clanID like ?;";

	public static String getClanRankOfaMember = "SELECT position FROM ( " +
            "SELECT ROW_NUMBER() OVER (ORDER BY mailID) AS position, mailID " +
            "FROM ClanRelation WHERE clanID = ?) AS ranked " +
            "WHERE mailID = ?";

	public static String getClanReqByMemberID = "select mailID from ClanRequest where mailID like ? and clanID like ?;";

	public static String pickAdminFromCoAdmins = "select C.mailID from ClanRelation C join Users U on C.mailID = U.mailID where C.role like 'CO_ADMIN' and clanID like ? order by U.score desc limit 1;";

	public static String pickAdminFromMembers = "select C.mailID from ClanRelation C join Users U on C.mailID = U.mailID where C.role like 'MEMBER' and clanID like ? order by U.score desc limit 1;";

	public static String makeAdminOftheClan = "update Clan set Admin = ? where clanID = ?;";
	
	public static String insertAdminInClanRelation = "insert into ClanRelation (clanID, mailID, role) values(?, ?, 'ADMIN');";

	public static String changeRoleOfMemberInClan = "update ClanRelation set role = ? where clanID = ? and mailID like ?;";

	public static String getUserNameFromMailID = "select username from Users where mailID like ?;";

	public static final String joinUserInClanByAdmin = "insert into ClanRelation (clanID, mailID) values(?, ?);";

	public static final String createNewClan = "insert into Clan (clanID, clanName, Admin) values(?, ?, ?);";

	public static final String leftFromClan = "delete from ClanRelation where mailID like ? and clanID like ?;";



	public static final String QuestionCompleted = "SELECT COUNT(*) FROM Solutions WHERE Q_ID = ? AND mailID = ? AND status='COMPLETED'";
	
	public static final String QuestionAttempt =
		    "SELECT Sol, Sol_ID FROM Solutions s " +
		    	    "INNER JOIN Languages l ON s.lang_ID = l.l_ID " +
		    	    "WHERE s.Q_ID = ? AND s.mailID = ? AND l.lang_name = ? AND s.status = 'ATTEMPTED'";
	public static final String isAnyAttemptedSolution =
		    "SELECT count(*) FROM Solutions s " +
		    	    "INNER JOIN Languages l ON s.lang_ID = l.l_ID " +
		    	    "WHERE s.Q_ID = ? AND s.mailID = ? AND l.lang_name = ? AND s.status = 'ATTEMPTED'";
	public static final String fetchQuestionDetails ="SELECT q.Q_ID, q.Q_name, q.description, q.functionString, q.testcaseJSON, q.example, q.levelID, q.Author, l.level_name, l.score, " +
		    "u.userName, u.score, u.streak, " +
		    "lr.l_ID, lr.funcName, lr.testCases, lang.lang_name " +
		    "FROM Questions q " +
		    "JOIN Levels l ON q.levelID = l.levelID " +
		    "JOIN Users u ON q.Author = u.mailID " +
		    "JOIN LanguageRelation lr ON q.Q_ID = lr.Q_ID " +
		    "JOIN Languages lang ON lr.l_ID = lang.l_ID " +
		    "WHERE q.Q_ID = ? AND q.status like 'APPROVED'";
	public static final String fetchAllQuestionDetails ="SELECT q.Q_ID, q.Q_name, q.description, q.levelID, q.Author, l.level_name, l.score, " +
		    "u.userName, u.score, u.streak " +
		    "FROM Questions q " +
		    "JOIN Levels l ON q.levelID = l.levelID " +
		    "JOIN Users u ON q.Author = u.mailID WHERE q.status like 'APPROVED'";
	public static final String fecthFuncName = 
	        "SELECT funcName " +
	        "FROM LanguageRelation lr " +
	        "JOIN Languages l ON lr.l_ID = l.l_ID " +
	        "WHERE l.lang_name like ? " +
	        "AND lr.Q_ID like ?";
	public static final String fetchSolutions ="SELECT Sol_ID,Sol, status FROM Solutions WHERE Q_ID = ? AND mailID = ? AND status=?";
	
	public static final String fetchAllSolutions ="SELECT s.Sol_ID, s.Sol, s.status,s.solDate, u.mailID, u.userName, u.score, u.streak " +
	            "FROM Solutions s " +
	            "JOIN Users u ON s.mailID = u.mailID " +
				"JOIN Languages l ON s.lang_ID = l.l_ID " +
	            "WHERE s.Q_ID = ? AND s.status = ? AND l.lang_name like ?";
	public static final String fetchLanguages="SELECT lang_name FROM Languages l " +
	            "JOIN LanguageRelation lr ON l.l_ID = lr.l_ID " +
	            "WHERE lr.Q_ID = ?";
	public static final String fetchTags="SELECT Tag_name FROM Tags t " +
            "JOIN TagsRelation tr ON t.Tag_ID = tr.Tag_ID " +
            "WHERE tr.Q_ID = ?";
	public static final String fetchAttemptCount="SELECT count(*) FROM Solutions WHERE Q_ID = ? AND status = ?";
	
	public static final String fetchCompletedCount="SELECT count(*) FROM Solutions WHERE Q_ID = ? AND status = ?";
	
	public static final String insertSolution="INSERT INTO Solutions (Sol_ID, mailID, Q_ID, lang_ID, solDate, status, solvedType) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String updateSolution="UPDATE Solutions SET Sol = ? WHERE Sol_ID = ?";
	
	public static final String fetchSolutionDetails="SELECT * FROM Solutions join Languages on l_ID=lang_ID join Users on Users.mailID=Solutions.mailID WHERE Sol_ID = ?";
	
	public static final String getUser="SELECT mailID, userName, score, streak FROM Users where mailID=?";
	
	public static final String insertQuestion="INSERT into Questions(Q_ID, Q_name,description,example,functionString,testcaseJSON,levelID,Author,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String approveQuestion="UPDATE Questions SET status = 'APPROVED' WHERE Q_ID = ?";
	
	public static final String getQuestion = "select Q_name from Questions where Q_ID like ?;";
	
	public static final String getSolution = "select Sol from Solutions where Sol_ID like ?";
	
	public static final String getSolutionDateOfAMonth = "select solDate from Solutions where MONTH(solDate)=? and mailID like ?;";
	
	public static final String getQIDforDate = "select Q_ID from Solutions where mailID like ? and solDate like ?;";
	
	public static final String getLevelName = "select levelID from Levels where level_name like ?;";

	public static final String getStreakDetails = "select Streakdate, streak from Users where mailID like ?";

	public static final String setStreak = "update Users set streak = 0 where mailID like ?";

	public static final String setStreakDate = "update Users set Streakdate = ? where mailID like ?";

	public static final String increaseScore = "UPDATE Users SET score = score + (SELECT score FROM Levels WHERE levelID = ?) WHERE mailID = ?";

	public static final String getFuncname = "select functionString from Questions where Q_ID like ?";

	public static final String getTestcase = "select testcaseJSON from Questions where Q_ID like ?";

	public static final String increaseStreak ="update Users set streak = ? where mailID like ?";

	public static final String isValidQuestionID = "SELECT Q_name FROM Questions WHERE Q_ID LIKE ?";

	public static final String getLevel = "SELECT levelID, level_name,score from Levels where levelID=?";
	
	public static final String insertTagRelation = "INSERT into TagsRelation values(?,?);";

	public static final String getTagId = "Select Tag_ID from Tags where Tag_name=?;";

    public static final String insertLanguageRelation = "insert into LanguageRelation values(?,?,?,?);";

    public static final String getLanguageID = "Select l_ID from Languages where lang_name=?;";

	public static final String getClansAndScores = "select clanName, COALESCE(SUM(Users.score), 0) AS total_score, ClanRelation.mailID from Clan left join ClanRelation on Clan.clanID = ClanRelation.clanID left join Users on ClanRelation.mailID = Users.mailID where role like '%ADMIN%' group by Clan.clanName order by total_score desc";
}
