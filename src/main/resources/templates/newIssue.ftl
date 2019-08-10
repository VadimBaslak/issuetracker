<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Create a new issue</h2>
    <div class="form-group mt-3">
        <form action="/newIssue" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(issueNameError??)?string('is-invalid', '')}"
                       value="<#if issue??>${issue.issueName}</#if>" name="issueName" placeholder="Issue name" />
                <#if issueNameError??>
                    <div class="invalid-feedback">
                        ${issueNameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                       value="<#if issue??>${issue.description}</#if>"name="description" placeholder="Description" />
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile" />
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </form>
    </div>
</@c.page>