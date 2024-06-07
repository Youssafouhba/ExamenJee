<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Formulaire</title>
</head>
<body>
<h1>Formulaire d'inscription</h1>
<form:form action="/form"  modelAttribute="employe">
    <div>
        <label for="name">Nom:</label>
        <form:input path="name" />
        <form:errors path="name" cssClass="error" />
    </div>
    <div>
        <label for="email">Email:</label>
        <form:input path="email" />
        <form:errors path="email" cssClass="error" />
    </div>
    <button type="submit">Envoyer</button>
</form:form>
</body>
</html>