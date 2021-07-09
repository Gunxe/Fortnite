package ch.bbcag.fortnite

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class TestHelper {

        fun atPositionOnView(recyclerViewId: Int, position: Int, targetViewId: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {

                var resources: Resources? = null
                var childView: View? = null

                override fun describeTo(description: Description) {
                    val idDescription = if (this.resources != null) {
                        try {
                            this.resources?.getResourceName(recyclerViewId)
                        } catch (var4: Resources.NotFoundException) {
                            "${Integer.valueOf(recyclerViewId)} (resource name not found)"
                        }
                    } else {
                        recyclerViewId.toString()
                    }

                    description.appendText("with id: $idDescription")
                }

                override fun matchesSafely(view: View): Boolean {
                    this.resources = view.resources

                    val recyclerView = view.rootView.findViewById(recyclerViewId) as? RecyclerView
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                    } else {
                        return false
                    }
                    return view == childView?.findViewById(targetViewId)
                }

            }
        }


}