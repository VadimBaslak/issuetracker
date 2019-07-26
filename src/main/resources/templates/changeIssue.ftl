<#import "parts/common.ftl" as c>
<@c.page>
    <a href="/main"><h4>Вернуться к списку задач</h4></a>
    <div>
        <h2>${issue.issueName}</h2>
        <span>${issue.status}</span><br>
        <span>${issue.startDate}</span><br>
        <span>${issue.author.getUsername()}</span><br>
        <span>${issue.description}</span><br>
    </div>
</@c.page>