<#import "parts/common.ftl" as c>
<@c.page>
    <div class="card" style="max-width: 50rem;">
        <div class="card-body">
            <h5 class="card-title">${issue.issueName}</h5>
            <ul class="list-group list-group-flush mb-4">
                <li class="list-group-item"><strong>Status:</strong> ${issue.status}</li>
                <li class="list-group-item"><strong>Date of creation:</strong> ${issue.startDate}</li>
                <li class="list-group-item"><strong>Author:</strong> ${issue.author.getUsername()}</li>
                <li class="list-group-item"><strong>Description:</strong> ${issue.description}</li>
            </ul>
                <#if issue.filename??>
                    <a href="/img/${issue.filename}"><img src="/img/${issue.filename}" class="mb-4 img-fluid"></a>
                </#if>
            <h6 class="card-subtitle mb-2 text-muted"><strong>List of comments:</strong></h6>
        </div>



        <div class="card m-1" style="max-width: 49rem;">
            <div class="card-body">
                <h3>Add comment: </h3>
                <form action="/issue" method="post">
                    <select name="status">
                        <#list statusList as status>
                            <option name="status">${status.getStatus()}</option>
                        </#list>
                    </select>
                    <br>
                    <input type="hidden" name="issueId" value="${issue.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    Comment<br>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>