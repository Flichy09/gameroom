<template>

  <div id="waiting">
    <div class="list_title">대기방 - 대기중인 방에 참여해요! </div>
    <vue-horizontal-list :items="waiting_games" :options="options" class="abc">
      <template v-slot:default="{ item }">
          <div class="image-container">

            <img :src="image_url[item.gameId-1]" />


            <div class="roominfo">
              <p>방 : {{ item.title }}</p>
              <p>{{item.nowUser}}/{{item.maxUser}}</p>
              <p>게임 : {{ item.gameName }}</p>
              <p>방장 : {{ item.ownerNickname }}</p>
            </div>

            <!-- 비밀방일 때 열쇠 띄워줌 v-if 처리 해주기 -->
          <div v-if="item.privateRoom">
          <img class="key" src="@/assets/key.png" alt="key">
          </div>

            <div v-if="loggedIn" class="btn" >
                  <div v-if="item.privateRoom">
                    <router-link class="btn_text" :to="`/gameroom/${item.id}/room_password`">
                      <div class="button button--brightness">입장</div>
                    </router-link>
                    </div>
                  <div v-else>
                    <router-link class="btn_text" :to="`/gameroom/${item.id}`">
                    <div class="button button--brightness">입장</div>
                  </router-link>
              </div>
              </div>

             <div v-if="!loggedIn" class="btn" id="enter">
                <router-link class="btn_text" :to="{ name: 'Login' }" >
                    <div class="button button--brightness">입장</div>
                  </router-link>
              </div>

            </div>
       

        <!-- </div> -->
      </template>
    </vue-horizontal-list>
    </div>

</template>


<script>
import { authComputed } from "@/store/helpers"
const SERVER_URL = process.env.VUE_APP_SERVER_URL
import VueHorizontalList from "vue-horizontal-list";
// import axios from 'axios'
import _ from "lodash"

export default {
  name:'WaitingRooms',
  components: {
     VueHorizontalList
    },
    data() {
    return {
      waiting_games: [0],
      image_url: [],

      options: {
        responsive: [
          { end: 576, size: 1 },
          { start: 576, end: 768, size: 2 },
          { start: 768, end: 992, size: 3 },
          { size: 4 },
        ],
      position: {
          start: 0,
        },
      autoplay: {
        play: true,
        speed: 4000,
        repeat: true,
      },
      },
    };
  },
  methods: {
    // entersession(){
    //   console.log('파람스')
    //   console.log(this.$route.params)
    //   axios.get(`${SERVER_URL}/conferences/${this.$route.params.roomid}`)
    // .then((res) => {
    //     console.log(res.status)
    //     if (res.status == 200) {
    //         this.canJoin = true;
    //     } else {
    //         this.canJoin = false;
    //     }
    //     if (!this.canJoin)
    //         return;
    // })
    // .catch(() => {
    //     this.$router.push({ name: 'MainPage' })
    //     this.canJoin = false;
    // });
    // } 
  },

   computed: {
    ...authComputed,
  },
    created(){
    this.$axios.get(`${SERVER_URL}/conferences`)
    .then((res) => {
      this.waiting_games = res.data
      // console.log(this.waiting_games)
      const url_value=_.sampleSize(_.range(500,600),6)

      for (var i=0; i<6; i++) {
        this.image_url.push(`https://unsplash.it/${url_value[i]}/${url_value[i]}/`)
      }



    })
  },
}
</script>
<style scoped>
@import './../../common/css/main.css';
#waiting{
  height: 28vh;
}
 p,h5 {
  font-size: 0.9vw;
  color:white;
}
.roominfo {
  /* display: flex; */
  position: absolute;
  margin: 2% 15%;
  text-align:initial;
  top: 0.8vh;
  
  
}
.image-container {
  border-radius: 10px;
  overflow: hidden;
  position: relative;
  width: 75%;
  /* 패딩 탑으로 직사각형으로 이미지 */
  padding-top: 20%;
  margin-left: 15%;
  box-shadow: 0px 6px 6px rgba(56, 56, 56, 0.753);
}
img {
  object-fit: cover;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  filter: brightness(40%);
  
}
.abc {
  margin: 0 5rem;
  /* height: 24vh; */
}
.key {
  width:20px;
  height:20px;
  top: 10%;
  left:85%;
  filter: brightness(100%);
}


</style>
