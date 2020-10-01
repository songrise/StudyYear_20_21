select Student.SId, Sname, Sage, Ssex, score
from Student , SC
where Student.SId = SC.SId 