<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pageCourante" type="Integer" required="true"%>
<%@ attribute name="nbComputers" type="Integer" required="true"%>
<%@ attribute name="lang" type="String" required="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<ul class="pagination">
	<c:choose>
		<c:when test="${lang == ''}">
			<!-- Gestion du cas précédent -->
			<c:if test="${pageCourante != null}">
				<c:if test="${pageCourante != 1}">
					<li><a
						href="/ComputerDatabaseMaven/dashboard?page=${pageCourante - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas par défaut sans num page -->
			<c:if test="${pageCourante == null}">
				<c:forEach begin="1" end="5" var="i">
					<c:choose>
						<c:when test="${i eq 1}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${nbComputers > 15}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=2"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

			</c:if>

			<!-- Gestion du cas page = 1 -->
			<c:if test="${pageCourante == 1}">
				<c:forEach begin="1" end="${pageCourante + 4}" var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${nbComputers > 15}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=2"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas page = 2 -->
			<c:if test="${pageCourante == 2}">
				<c:forEach begin="1" end="${pageCourante + 3}" var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${nbComputers > 30}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=3"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas page > 2 -->
			<c:if test="${pageCourante > 2}">
				<c:forEach begin="${pageCourante - 2}" end="${pageCourante + 2}"
					var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<c:if test="${nbComputers > (15 * i)}">
								<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}">${i}</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<fmt:parseNumber var="iFormat" integerOnly="true" type="number"
					value="${nbComputers / 15}" />
				<c:if test="${pageCourante == iFormat}">
					<c:if test="${nbComputers % 15 != 0 }">
						<li><a
							href="/ComputerDatabaseMaven/dashboard?page=${iFormat + 1}">${iFormat + 1}</a></li>
					</c:if>
				</c:if>
				<c:if test="${nbComputers > (15 * pageCourante)}">
					<li><a
						href="/ComputerDatabaseMaven/dashboard?page=${pageCourante + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>
		</c:when>
		<c:otherwise>
			<!-- Gestion du cas précédent -->
			<c:if test="${pageCourante != null}">
				<c:if test="${pageCourante != 1}">
					<li><a
						href="/ComputerDatabaseMaven/dashboard?page=${pageCourante - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas par défaut sans num page -->
			<c:if test="${pageCourante == null}">
				<c:forEach begin="1" end="5" var="i">
					<c:choose>
						<c:when test="${i eq 1}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${nbComputers > 15}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=2&language=${lang}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

			</c:if>

			<!-- Gestion du cas page = 1 -->
			<c:if test="${pageCourante == 1}">
				<c:forEach begin="1" end="${pageCourante + 4}" var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${nbComputers > 15}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=2&language=${lang}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas page = 2 -->
			<c:if test="${pageCourante == 2}">
				<c:forEach begin="1" end="${pageCourante + 3}" var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${nbComputers > 30}">
					<li><a href="/ComputerDatabaseMaven/dashboard?page=3&language=${lang}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>

			<!-- Gestion du cas page > 2 -->
			<c:if test="${pageCourante > 2}">
				<c:forEach begin="${pageCourante - 2}" end="${pageCourante + 2}"
					var="i">
					<c:choose>
						<c:when test="${pageCourante eq i}">
							<li class="active"><a
								href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<c:if test="${nbComputers > (15 * i)}">
								<li><a href="/ComputerDatabaseMaven/dashboard?page=${i}&language=${lang}">${i}</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<fmt:parseNumber var="iFormat" integerOnly="true" type="number"
					value="${nbComputers / 15}" />
				<c:if test="${pageCourante == iFormat}">
					<c:if test="${nbComputers % 15 != 0 }">
						<li><a
							href="/ComputerDatabaseMaven/dashboard?page=${iFormat + 1}&language=${lang}">${iFormat + 1}</a></li>
					</c:if>
				</c:if>
				<c:if test="${nbComputers > (15 * pageCourante)}">
					<li><a
						href="/ComputerDatabaseMaven/dashboard?page=${pageCourante + 1}&language=${lang}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</c:if>
		</c:otherwise>
	</c:choose>
</ul>