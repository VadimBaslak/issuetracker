<#import "parts/common.ftl" as c>
<@c.page>
    <div class="card" style="max-width: 50rem;">
        <div class="card-body">
            <h5 class="card-title">${issue.issueName}</h5>
            <ul class="list-group list-group-flush mb-4">
                <li class="list-group-item"><strong>Date of creation:</strong> ${issue.startDate}</li>
                <li class="list-group-item"><strong>Status:</strong> ${issue.status}</li>
                <li class="list-group-item"><strong>Author:</strong> ${issue.author.getUsername()}</li>
                <li class="list-group-item"><strong>Description:</strong> ${issue.description}</li>
            </ul>
                <#if issue.filename??>
                    <a href="/img/${issue.filename}"><img src="/img/${issue.filename}" class="mb-4 img-fluid"></a>
                </#if>
            <h6 class="card-subtitle mb-2 text-muted"><strong>List of comments:</strong></h6>
            <ul class="list-group list-group-flush mb-4">
                    <#list comments as comment>
                <li class="list-group-item">
                    <span class="text-break"><strong>Date of the comment:</strong> ${comment.dateComment}</span><br>
                    <span class="text-break"><strong>Status:</strong> ${comment.status}</span><br>
                    <span class="text-break"><strong>Author:</strong> ${comment.commentAuthor.getUsername()}</span><br>
                    <span class="text-break"><strong>Comment:</strong> ${comment.textComment}</span><br>
                </li>
                <#else>
                    No comments
                </#list>

            </ul>
        </div>
        <div class="card m-1" style="max-width: 49rem;">
            <div class="card-body">
                <h3>New comment: </h3>
                <form action="/issue" method="post">
                    <select name="status">
                        <#list statusList as status>
                            <option name="status">${status.getStatus()}</option>
                        </#list>
                    </select>
                    <input type="text" class="form-control mt-2 mb-2 ${(textCommentError??)?string('is-invalid', '')}"
                           value="<#if comment??></#if> "name="textComment" placeholder="Comment text" />
                    <#if textCommentError??>
                        <div class="invalid-feedback">
                            ${textCommentError}
                        </div>
                    </#if>
                    <input type="hidden" name="issueId" value="${issue.id}" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-primary mt-2">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>