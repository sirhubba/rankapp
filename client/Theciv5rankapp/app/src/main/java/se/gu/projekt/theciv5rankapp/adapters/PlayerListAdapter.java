package se.gu.projekt.theciv5rankapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import se.gu.projekt.theciv5rankapp.R;
import se.gu.projekt.theciv5rankapp.classes.Player;

public class PlayerListAdapter extends ArrayAdapter<Player> {

    private static final String TAG = "PlayerListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    public static class ViewHolder {
        public TextView playerName;
        public TextView rank;
        public TextView rating;
        public TextView steamId;

    }

    /**
     * För att få effektivare scrolling i ListView finns två algoritmer hämtade på developer-sidan.
     * Av dessa två algoritmer är en för att ladda items i listan på ett smartare
     * sätt och en för att visa korrekta animationer för att scrolla upp/ner.
     */


    public PlayerListAdapter(Context context, int resource, ArrayList<Player> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Hämta info om spelare
        String playerName = getItem(position).playerName();
        int rank = getItem(position).rank();
        int rating = getItem(position).rating();
        String steamId = getItem(position).steamId();

        //Skapa spelare med info från raderna ovanför
        Player player = new Player(playerName, rank, rating, steamId);

        //Variabel som kommer visa animationen för effektiv scrolling
        final View result;

        //Viewholder object
        ViewHolder holder;

        //Om man inte varit på positionen innan gör första statement annars ladda det sparade
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.playerName = (TextView) convertView.findViewById(R.id.Player);
            holder.rank = (TextView) convertView.findViewById(R.id.Rank);
            holder.rating = (TextView) convertView.findViewById(R.id.Rating);

            result = convertView;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        //Algoritm för att visa  rätt animation
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        //Parse ints för att få in dom i textview
        String parsedRank = Integer.toString(rank);
        String parsedRating = Integer.toString(rating);

        holder.playerName.setText(playerName);
        holder.rank.setText(parsedRank);
        holder.rating.setText(parsedRating);
        //holder.steamId.setText(steamId);

        return convertView;
    }
}
