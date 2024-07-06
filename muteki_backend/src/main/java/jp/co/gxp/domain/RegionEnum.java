package jp.co.gxp.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Getter
public class RegionEnum {

    public enum Region {
        北海道_東北(EnumSet.of(Prefecture.北海道, Prefecture.青森県, Prefecture.岩手県, Prefecture.宮城県, Prefecture.秋田県, Prefecture.山形県, Prefecture.福島県)),
        関東(EnumSet.of(Prefecture.茨城県, Prefecture.栃木県, Prefecture.群馬県, Prefecture.埼玉県, Prefecture.千葉県, Prefecture.東京都, Prefecture.神奈川県)),
        北陸_甲信越(EnumSet.of(Prefecture.新潟県, Prefecture.富山県, Prefecture.石川県, Prefecture.福井県, Prefecture.山梨県, Prefecture.長野県)),
        東海(EnumSet.of(Prefecture.岐阜県, Prefecture.静岡県, Prefecture.愛知県, Prefecture.三重県)),
        関西(EnumSet.of(Prefecture.滋賀県, Prefecture.京都府, Prefecture.大阪府, Prefecture.兵庫県, Prefecture.奈良県, Prefecture.和歌山県)),
        中国_四国(EnumSet.of(Prefecture.鳥取県, Prefecture.島根県, Prefecture.岡山県, Prefecture.広島県, Prefecture.山口県, Prefecture.徳島県, Prefecture.香川県, Prefecture.愛媛県, Prefecture.高知県)),
        九州_沖縄(EnumSet.of(Prefecture.福岡県, Prefecture.佐賀県, Prefecture.長崎県, Prefecture.熊本県, Prefecture.大分県, Prefecture.宮崎県, Prefecture.鹿児島県, Prefecture.沖縄県));

        private final Set<Prefecture> prefectures;

        Region(Set<Prefecture> prefectures) {
            this.prefectures = prefectures;
        }

        public Set<Prefecture> getPrefectures() {
            return prefectures;
        }

        // 地域で都道府県を取得する
        public static List<String> getPrefecturesFromRegions(List<String> regions) {
            if(regions!=null) {
                List<String> prefectures = new ArrayList<>();
                for (String regionName : regions) {
                    Region region = Region.valueOf(regionName);
                    for (Prefecture prefecture : region.getPrefectures()) {
                        prefectures.add(prefecture.name());
                    }
                }
                return prefectures;
            }
            else return null;
        }
    }

    public enum Prefecture {
        北海道,
        青森県,
        岩手県,
        宮城県,
        秋田県,
        山形県,
        福島県,
        茨城県,
        栃木県,
        群馬県,
        埼玉県,
        千葉県,
        東京都,
        神奈川県,
        新潟県,
        富山県,
        石川県,
        福井県,
        山梨県,
        長野県,
        岐阜県,
        静岡県,
        愛知県,
        三重県,
        滋賀県,
        京都府,
        大阪府,
        兵庫県,
        奈良県,
        和歌山県,
        鳥取県,
        島根県,
        岡山県,
        広島県,
        山口県,
        徳島県,
        香川県,
        愛媛県,
        高知県,
        福岡県,
        佐賀県,
        長崎県,
        熊本県,
        大分県,
        宮崎県,
        鹿児島県,
        沖縄県
    }
}
